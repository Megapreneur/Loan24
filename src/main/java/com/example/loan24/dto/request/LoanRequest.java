package com.example.loan24.dto.request;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.math.BigDecimal;
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoanRequest {
    private String email;
    private BigDecimal loanAmount;
    private String loanPurpose;
    private String loanPlan;
    private String guarantorName;
    private String guarantorPhoneNumber;

}
