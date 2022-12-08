package com.example.loan24.service;

import com.example.loan24.data.model.Loan;
import com.example.loan24.data.model.Payment;
import com.example.loan24.dto.request.*;
import com.example.loan24.dto.response.*;
import com.example.loan24.exception.UserAlreadyExistException;

import java.util.List;


public interface Loan24Service {
    RegisterUserResponse register(RegisterUserRequest request) throws UserAlreadyExistException;
    LoanResponse applyForLoan(LoanRequest request);
    CustomerDto findUser(FindUserRequest request);
    List<Loan>searchForLoans(String email);
    PaymentResponse makePayment(PaymentRequest request);
    boolean isLoanApproved();

    List<Payment> paymentHistory(String email);
}
