package com.example.PgReport.service;

import com.example.PgReport.model.RefreshTokenBO;

public interface RefreshTokenService {
    public RefreshTokenBO findByToken(String token);
    public boolean verifyExpiration(RefreshTokenBO token);
}
