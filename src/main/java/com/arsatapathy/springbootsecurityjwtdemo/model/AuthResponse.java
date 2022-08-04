package com.arsatapathy.springbootsecurityjwtdemo.model;

public class AuthResponse {

    private final String TOKEN;

    public AuthResponse(String JWT_TOKEN) {
        this.TOKEN = JWT_TOKEN;
    }

    public String getTOKEN() {
        return TOKEN;
    }
}
