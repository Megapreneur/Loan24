package com.example.loan24.service;

import com.example.loan24.data.model.TokenVerification;
import com.example.loan24.data.repository.TokenVerificationRepository;
import com.example.loan24.exception.VerificationTokenException;
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
        TokenVerification tokenVerification =
                verificationRepository.findByToken(token)
                .orElseThrow(() -> new VerificationTokenException("token not found"));
        if (isTokenNotExpired(tokenVerification)) return true;
        throw new VerificationTokenException("token has expired");
    }

    private boolean isTokenNotExpired(TokenVerification tokenVerification) {
        return LocalDateTime.now().isBefore(tokenVerification.getExpiresAt());
    }
}
