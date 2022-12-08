package com.example.loan24.data.model;

import com.example.loan24.data.model.enumClass.PaymentType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate dateOfPayment;
    private BigDecimal amount;
    @Enumerated(value = EnumType.STRING)
    private PaymentType paymentType;
    @ManyToOne
    @JoinColumn
    private Loan loan;
    @JoinColumn(name = "user_id")
    @ManyToOne
    private Customer user;

}
