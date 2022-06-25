package com.example.PgReport.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponse {

    private String response;

    public AuthenticationResponse(String response) {
        this.response = response;
    }
}
