package com.ldnhat.stdiomanagement.common.provider;

import com.ldnhat.stdiomanagement.common.constant.Constant;
import com.ldnhat.stdiomanagement.common.custom.CustomUserDetails;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {

    //private secret
    @Value("${app.jwt.secret}")
    private String JWT_SECRET;

    private final long JWT_EXPIRATION = 604800000L;

    public String generateToken(CustomUserDetails userDetails){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

        return Jwts.builder()
                .setSubject(Long.toString(userDetails.getUserEntity().getId()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public Long getUserIdFromJwt(String token){
        Claims claims = Jwts.parser()
                            .setSigningKey(JWT_SECRET)
                            .parseClaimsJws(token)
                            .getBody();
        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error(Constant.INVALID_JWT_TOKEN);
        } catch (ExpiredJwtException ex) {
            log.error(Constant.EXPIRED_JWT_TOKEN);
        } catch (UnsupportedJwtException ex) {
            log.error(Constant.UNSUPPORTED_JWT_TOKEN);
        } catch (IllegalArgumentException ex) {
            log.error(Constant.JWT_CLAIMS_STRING_IS_EMPTY);
        }
        return false;
    }
}
