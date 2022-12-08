package com.example.loan24.security.config;

import com.example.loan24.security.filter.Loan24AuthenticationFilter;
import com.example.loan24.security.filter.Loan24AuthorizationFilter;
import com.example.loan24.security.jwt.JwtUtil;
import com.example.loan24.security.manager.Loan24AuthenticationManager;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final JwtUtil jwtUtil;

    private final Loan24AuthenticationManager loan24AuthenticationManager;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        UsernamePasswordAuthenticationFilter filter = new Loan24AuthenticationFilter(loan24AuthenticationManager, jwtUtil);
        filter.setFilterProcessesUrl("/api/v1/loan24/login");

        return http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers( HttpMethod.POST,"/api/v1/loan24/admin/register",
                        "/api/v1/loan24/user/register")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/loan24/user/applyForLoan/",
                         "/api/v1/loan24/user/findLoan/{email}",
                        "/api/v1/loan24/user/makePayment/",
                        "/api/v1/loan24/user/findPayment/{email}")
                .hasAnyAuthority("BORROWER")
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/loan24/admin/findUser/")
                .hasAnyAuthority("LENDER")
                .and()
                .addFilter(filter)
                .addFilterBefore(new Loan24AuthorizationFilter(), Loan24AuthenticationFilter.class)
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .build();
    }
}
