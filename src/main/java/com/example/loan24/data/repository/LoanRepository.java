package com.example.loan24.data.repository;

import com.example.loan24.data.model.Loan;
import com.example.loan24.data.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    Optional<Loan> findUserById(Long id);

    List<Loan> findUser(Customer user);
}

