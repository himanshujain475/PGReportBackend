package com.example.PgReport.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponse {

    private String response;

    private String status;

    public AuthenticationResponse(String status,String response) {
        this.response = response;
        this.status=  status;
    }
}
