package com.example.loan24.security.filter;

import com.example.loan24.data.model.Customer;
import com.example.loan24.security.manager.Loan24AuthenticationManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@AllArgsConstructor
public class Loan24AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final Loan24AuthenticationManager loan24AuthenticationManager;
    ObjectMapper mapper = new ObjectMapper();

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
        Customer user;
        try {
            user = mapper.readValue(request.getReader(), Customer.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String email = user.getEmail();
        String password = user.getPassword();

        var authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authenticatedToken = loan24AuthenticationManager.authenticate(authenticationToken);
        if (authenticatedToken != null){
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authenticationToken);
            return authenticationToken;
        }
        throw new BadCredentialsException("Invalid User");
    }
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException{

    }

}
