package com.example.loan24.data.model;

import com.example.loan24.data.model.enumClass.PaymentType;
import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate dateOfPayment;
    private BigInteger amount;
    private PaymentType paymentType;
    @ManyToOne
    @JoinColumn
    private Loan loan;

}
