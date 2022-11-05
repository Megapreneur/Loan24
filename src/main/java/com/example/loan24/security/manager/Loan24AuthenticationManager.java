package com.example.loan24.security.manager;

import com.example.loan24.security.provider.Loan24AuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Loan24AuthenticationManager implements AuthenticationManager {

    private final Loan24AuthenticationProvider loan24AuthenticationProvider;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }
}
