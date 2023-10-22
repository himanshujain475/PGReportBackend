package com.example.PgReport.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponse {

    private String token;

    private String refreshToken;

    private String salt;

    private String status;

    public AuthenticationResponse(String status,String token,String refreshToken,String salt) {
        this.refreshToken = refreshToken;
        this.token = token;
        this.status=  status;
        this.salt = salt;
    }
}
