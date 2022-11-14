package com.example.loan24.security.provider;

import com.example.loan24.utils.Loan24UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Loan24AuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private Loan24UserDetailService userDetailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails = userDetailService.loadUserByUsername((String) authentication.getPrincipal());
        if (userDetails != null){
            if (isMatches(authentication, userDetails)){
                return new UsernamePasswordAuthenticationToken(userDetails, authentication.getCredentials(),
                        authentication.getAuthorities());
            }
            throw new BadCredentialsException("Incorrect password!!!");
        }
        throw new UsernameNotFoundException("Email not found!!!");
    }

    private boolean isMatches(Authentication authentication, UserDetails userDetails) {
        return passwordEncoder.matches((String) authentication.getCredentials(), userDetails.getPassword());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        Class<UsernamePasswordAuthenticationToken> appAuthType = UsernamePasswordAuthenticationToken.class;
        return authentication.equals(appAuthType);
    }
}
