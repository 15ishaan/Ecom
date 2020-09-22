package com.example.eshop.model;

public class AuthenticationResponse {

    //returning token
    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
