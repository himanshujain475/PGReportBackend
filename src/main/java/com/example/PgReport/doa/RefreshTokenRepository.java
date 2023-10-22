package com.example.PgReport.doa;


import com.example.PgReport.model.RefreshTokenBO;

import java.util.Optional;

public interface RefreshTokenRepository {
    RefreshTokenBO findByToken(String token);

    void deleteByRefreshToken(String token);

    void save(RefreshTokenBO refreshTokenBO);
}
