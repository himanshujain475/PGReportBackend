package com.example.PgReport.service;

import com.example.PgReport.Exception.TokenRefreshException;
import com.example.PgReport.User.UserBOService;
import com.example.PgReport.User.UserRepo;
import com.example.PgReport.doa.RefreshTokenRepository;
import com.example.PgReport.model.RefreshTokenBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service

public class RefreshTokenServiceImpl implements RefreshTokenService{

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepo userRepo;

    @Value("${spring.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;



    public RefreshTokenBO findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshTokenBO createRefreshToken(Long mobile ) {
        RefreshTokenBO refreshToken = new RefreshTokenBO();

        refreshToken.setMobile(userRepo.findByMobile(mobile).getMobile());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

         refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public boolean verifyExpiration(RefreshTokenBO token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.deleteByRefreshToken(token.getToken());
            return  false;
        }

        return true;
    }

//    @Transactional
//    public void deleteByUserId(Long userId) {
//        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
//    }
}
