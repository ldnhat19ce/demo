package com.ldnhat.stdiomanagement.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class LoginResponseDto {

    private final String accessToken;
    private String tokenType = "Bearer";

}
