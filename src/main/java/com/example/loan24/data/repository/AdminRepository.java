package com.example.loan24.data.repository;

import com.example.loan24.data.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    boolean existByEmail(String email);

    Optional<Admin> findByEmail(String email);
}
