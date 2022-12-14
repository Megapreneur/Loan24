package com.example.loan24.controller;

import com.example.loan24.data.model.Loan;
import com.example.loan24.data.model.Payment;
import com.example.loan24.dto.request.*;
import com.example.loan24.dto.response.*;
import com.example.loan24.exception.UserAlreadyExistException;
import com.example.loan24.service.AdminService;
import com.example.loan24.service.Loan24Service;
import com.example.loan24.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loan24/")
public class Loan24Controller {
    @Autowired
    private Loan24Service loan24Service;
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    @PostMapping("admin/register")
    public RegisterUserResponse register(@RequestBody RegisterAdminRequest request) throws UserAlreadyExistException{
        return adminService.register(request);
    }

    @PostMapping("user/register")
    public RegisterUserResponse register(@RequestBody RegisterUserRequest request) throws UserAlreadyExistException {
        return loan24Service.register(request);
    }

    @PostMapping("login/")
    public LoginUserResponse login(@RequestBody LoginUserRequest request){
        return userService.login(request);
    }

    @PostMapping("user/applyForLoan/")
    public LoanResponse applyForLoan(@RequestBody LoanRequest request){
        return loan24Service.applyForLoan(request);
    }

    @GetMapping("admin/findUser/")
    public CustomerDto findUser(@RequestBody FindUserRequest request){
        return loan24Service.findUser(request);
    }

    @GetMapping("user/findLoan/{email}")
    public List<Loan> searchForLoan(@PathVariable String email){
        return loan24Service.searchForLoans(email);
    }

    @PatchMapping("user/makePayment/")
    public PaymentResponse makePayment(@RequestBody PaymentRequest request){
        return loan24Service.makePayment(request);
    }
    @GetMapping("user/findPayment/{email}")
    public List<Payment> getPaymentHistory(@PathVariable String email){
        return loan24Service.paymentHistory(email);
    }


}
