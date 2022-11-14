package com.example.loan24.utils;

import lombok.Getter;

import java.security.SecureRandom;

@Getter
public class TokenUtil {
    public static String generateToken() {
        SecureRandom random = new SecureRandom();
        return String.valueOf(10000 + random.nextInt(99999));
    }
}
