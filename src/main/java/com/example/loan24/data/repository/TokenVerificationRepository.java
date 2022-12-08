package com.example.loan24.data.repository;


import com.example.loan24.data.model.TokenVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenVerificationRepository extends JpaRepository<TokenVerification, Long> {

    Optional<TokenVerification> findByToken(String token);

}
