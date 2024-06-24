package com.example.security.entity.dto;

public class AuthResponseDto {
    private String accessToken;
    private String tokenType = "Bearer ";

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public AuthResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
