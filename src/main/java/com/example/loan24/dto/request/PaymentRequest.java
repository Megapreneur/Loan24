package com.example.loan24.dto.request;

import com.example.loan24.data.model.enumClass.PaymentType;
import lombok.*;

import java.math.BigDecimal;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private String email;
    private BigDecimal amount;
    private PaymentType paymentType;
}
