package com.example.loan24.data.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal loanAmount;
    private BigDecimal balance;
    private String loanDuration;
    private String loanPurpose;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate dueDate;
    @JoinColumn(name = "user_id")
    @ManyToOne
    private Customer user;
    private String guarantorName;
    @Column(unique = true)
    private String guarantorPhoneNumber;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate dateOfLoan = LocalDate.now();
}
