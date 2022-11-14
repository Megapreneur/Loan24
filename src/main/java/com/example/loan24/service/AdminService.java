package com.example.loan24.service;

import com.example.loan24.dto.request.RegisterAdminRequest;
import com.example.loan24.dto.response.LoanResponse;
import com.example.loan24.dto.response.RegisterUserResponse;
import com.example.loan24.exception.UserAlreadyExistException;

public interface AdminService {
    RegisterUserResponse register(RegisterAdminRequest request) throws UserAlreadyExistException;
    LoanResponse approveLoan(String email);

}
