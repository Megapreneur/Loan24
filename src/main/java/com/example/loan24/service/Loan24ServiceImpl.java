package com.example.loan24.service;

import com.example.loan24.data.model.Loan;
import com.example.loan24.data.model.Payment;
import com.example.loan24.data.model.User;
import com.example.loan24.data.repository.LoanRepository;
import com.example.loan24.data.repository.PaymentRepository;
import com.example.loan24.data.repository.UserRepository;
import com.example.loan24.dto.request.*;
import com.example.loan24.dto.response.LoanResponse;
import com.example.loan24.dto.response.LoginUserResponse;
import com.example.loan24.dto.response.PaymentResponse;
import com.example.loan24.dto.response.RegisterUserResponse;
import com.example.loan24.exception.Loan24Exception;
import com.example.loan24.exception.PasswordDoesNotMatchException;
import com.example.loan24.exception.UserAlreadyExistException;
import com.example.loan24.exception.InvalidUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class Loan24ServiceImpl implements Loan24Service {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private PaymentRepository paymentRepository;



    @Override
    public RegisterUserResponse register(RegisterUserRequest request) throws UserAlreadyExistException {
        if (userRepository.existsByEmail(request.getEmail())) throw new UserAlreadyExistException("User Already Exist!!!");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (request.getPassword().equals(request.getConfirmPassword())){
            User user = User.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .dob(LocalDate.parse(request.getDob(), dateTimeFormatter))
                    .accountNumber(request.getAccountNumber())
                    .address(request.getAddress())
                    .nin(request.getNin())
                    .bankName(request.getBankName())
                    .gender(request.getGender())
                    .confirmPassword(request.getConfirmPassword())
                    .phoneNumber(request.getPhoneNumber())
                    .occupation(request.getOccupation())
                    .password(request.getPassword())
                    .build();

            User savedUser = userRepository.save(user);
            return RegisterUserResponse.builder()
                    .message("Welcome to Loan24 " + savedUser.getName() + ". Your registration was successful")
                    .build();
        }
        throw new PasswordDoesNotMatchException("Your password does not match");
    }

    @Override
    public LoginUserResponse login(LoginUserRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if (user.isPresent()){
            if (user.get().getPassword().equals(request.getPassword())){
                return LoginUserResponse.builder()
                        .message("Welcome back " + user.get().getName())
                        .build();
            }
            throw new InvalidUserException("Invalid login details!!!");
        }
        throw new InvalidUserException("Invalid login details!!!");
    }

    @Override
    public LoanResponse applyForLoan(LoanRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if (user.isPresent()){
            Optional<Loan> loan = loanRepository.findUserById(user.get().getId());
            if (loan.isPresent()){
                if (loan.get().getBalance().intValue() > 0){
                    throw new Loan24Exception("You have to pay your outstanding loan of " + loan.get().getBalance());
                }
                Loan newLoan = Loan
                        .builder()
                        .loanAmount(request.getLoanAmount())
                        .loanDuration(request.getLoanPlan())
                        .loanPurpose(request.getLoanPurpose())
                        .guarantorName(request.getGuarantorName())
                        .guarantorPhoneNumber(request.getGuarantorPhoneNumber())
                        .build();
                Loan savedLoan = loanRepository.save(newLoan);
                return LoanResponse
                        .builder()
                        .message("Your loan request of ₦" + savedLoan.getLoanAmount() + " has been granted")
                        .build();
            }
        }
     throw new InvalidUserException("Invalid user");
    }

    @Override
    public User findUser(FindUserRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if (user.isPresent()){
            return user.get();
        }
        throw new InvalidUserException("Invalid login details!!!");
    }

    @Override
    public List<Loan> searchForLoans(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()){
            List<Loan> loanHistory = loanRepository.findUser(user.get());
            if(!loanHistory.isEmpty()){
                return loanHistory;
            }
            throw new Loan24Exception("You have no loan history");
        }
        throw new InvalidUserException("Invalid login details!!!");
    }

    @Override
    public PaymentResponse makePayment(PaymentRequest request){
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if (user.isPresent()){
            Optional<Loan> userLoan = loanRepository.findUserById(user.get().getId());
            if (userLoan.isPresent()){
                if (request.getAmount().intValue() > 0){
                    if (request.getAmount().intValue() <= userLoan.get().getLoanAmount().intValue()){
                        Payment payment = Payment
                                .builder()
                                .paymentType(request.getPaymentType())
                                .amount(request.getAmount())
                                .build();
                        userLoan.get().setBalance(userLoan.get().getBalance().subtract(request.getAmount()));
                        loanRepository.save(userLoan.get());
                        Payment savedPayment = paymentRepository.save(payment);
                        return PaymentResponse
                                .builder()
                                .message("Your payment was successful")
                                .build();
                    }
                    throw new Loan24Exception("You can't pay more than you owe");
                }
                throw new Loan24Exception("AMount is invalid");
            }

        }
        throw new InvalidUserException("Invalid login details!!!");
    }
}
