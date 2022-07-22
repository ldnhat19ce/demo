package com.ldnhat.stdiomanagement.controller.api;

import com.ldnhat.stdiomanagement.common.custom.CustomUserDetails;
import com.ldnhat.stdiomanagement.common.provider.JwtTokenProvider;
import com.ldnhat.stdiomanagement.dto.LoginRequestDto;
import com.ldnhat.stdiomanagement.dto.LoginResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("LoginApi")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider provider;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, JwtTokenProvider provider) {
        this.authenticationManager = authenticationManager;
        this.provider = provider;
    }

    @PostMapping("/login")
    public LoginResponseDto authenticationUser(@RequestBody LoginRequestDto requestDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.getUsername(),
                        requestDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = provider.generateToken((CustomUserDetails) authentication.getPrincipal());

        return new LoginResponseDto(jwt);
    }
}
