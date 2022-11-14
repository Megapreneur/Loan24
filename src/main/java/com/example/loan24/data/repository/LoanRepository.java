package com.example.loan24.data.repository;

import com.example.loan24.data.model.Loan;
import com.example.loan24.data.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    Optional<Loan> findUserById(long id);

    List<Loan> findLoanByUserId(Long id);

//    List<Loan> findUser(Long id);
//    List<Loan> findUser(Customer user);
}

