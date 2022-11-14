package com.example.loan24.service;

import com.example.loan24.data.model.TokenVerification;

public interface TokenVerificationService {
    TokenVerification createToken(String email);
    boolean isValidTokenVerification(String token);
}
