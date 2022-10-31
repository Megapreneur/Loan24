package com.example.loan24.dto.request;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.math.BigInteger;
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoanRequest {
    @Email
    @Valid
    private String email;
    private BigInteger loanAmount;
    private String loanPurpose;
    private String loanPlan;

}
