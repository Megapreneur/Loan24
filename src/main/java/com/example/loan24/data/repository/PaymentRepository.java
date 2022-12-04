package com.example.loan24.data.repository;

import com.example.loan24.data.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findPaymentByUserId(Long id);

}
