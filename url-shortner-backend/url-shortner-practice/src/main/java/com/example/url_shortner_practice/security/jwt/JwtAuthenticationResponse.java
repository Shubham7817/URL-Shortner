package com.example.url_shortner_practice.security.jwt;

public class JwtAuthenticationResponse {
    private String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    // Add getter and setter if not using Lombok
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
