package com.example.PgReport.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {

    private  Long mobile;
    private String pin;

    private String salt;
}
