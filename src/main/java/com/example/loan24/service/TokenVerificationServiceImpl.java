package com.example.loan24.service;

import com.example.loan24.data.model.TokenVerification;
import com.example.loan24.data.repository.TokenVerificationRepository;
import com.example.loan24.utils.TokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class TokenVerificationServiceImpl implements TokenVerificationService{

    private final TokenVerificationRepository verificationRepository;
    @Override
    public TokenVerification createToken(String email) {
        TokenVerification tokenVerification = TokenVerification.builder()
                .token(TokenUtil.generateToken())
                .userEmail(email)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().minusMinutes(5))
                .build();
        return verificationRepository.save(tokenVerification);
    }

    @Override
    public boolean isValidTokenVerification(String token) {
        return false;
    }
}
