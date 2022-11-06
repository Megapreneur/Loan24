package com.example.loan24.service;

import com.example.loan24.data.model.Loan;
import com.example.loan24.data.model.Customer;
import com.example.loan24.dto.request.*;
import com.example.loan24.dto.response.LoanResponse;
import com.example.loan24.dto.response.LoginUserResponse;
import com.example.loan24.dto.response.PaymentResponse;
import com.example.loan24.dto.response.RegisterUserResponse;
import com.example.loan24.exception.UserAlreadyExistException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Loan24Service {
    RegisterUserResponse register(RegisterUserRequest request) throws UserAlreadyExistException;
//    LoginUserResponse login(LoginUserRequest request);
    LoanResponse applyForLoan(LoanRequest request);
    Customer findUser(FindUserRequest request);
    List<Loan>searchForLoans(String email);
    PaymentResponse makePayment(PaymentRequest request);
}
