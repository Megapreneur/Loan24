package com.example.loan24.service;

import com.example.loan24.data.model.Loan;
import com.example.loan24.data.model.Payment;
import com.example.loan24.data.model.Customer;
import com.example.loan24.data.model.enumClass.Authority;
import com.example.loan24.data.repository.LoanRepository;
import com.example.loan24.data.repository.PaymentRepository;
import com.example.loan24.data.repository.CustomerRepository;
import com.example.loan24.dto.request.*;
import com.example.loan24.dto.response.LoanResponse;
import com.example.loan24.dto.response.PaymentResponse;
import com.example.loan24.dto.response.RegisterUserResponse;
import com.example.loan24.exception.Loan24Exception;
import com.example.loan24.exception.PasswordDoesNotMatchException;
import com.example.loan24.exception.UserAlreadyExistException;
import com.example.loan24.exception.InvalidUserException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class Loan24ServiceImpl implements Loan24Service {

    @Autowired
    private  CustomerRepository customerRepository;

    @Autowired
    private  LoanRepository loanRepository;

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private  ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public RegisterUserResponse register(RegisterUserRequest request) throws UserAlreadyExistException {
        if (customerRepository.existsByEmail(request.getEmail())) throw new UserAlreadyExistException("User Already Exist!!!");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (request.getPassword().equals(request.getConfirmPassword())){
            Customer savedUser = getCustomer(request, dateTimeFormatter);
            return RegisterUserResponse.builder()
                    .message("Welcome to Loan24 " + savedUser.getName() + ". Your registration was successful")
                    .build();
        }
        throw new PasswordDoesNotMatchException("Your password does not match");
    }

    private Customer getCustomer(RegisterUserRequest request, DateTimeFormatter dateTimeFormatter) {
        Customer newCustomer = modelMapper.map(request, Customer.class);
        newCustomer.setDob(LocalDate.parse(request.getDob(), dateTimeFormatter));
        newCustomer.getAuthority().add(Authority.BORROWER);
        newCustomer.setPassword(passwordEncoder.encode(request.getPassword()));
        return customerRepository.save(newCustomer);
    }

    @Override
    public LoanResponse applyForLoan(LoanRequest request) {
        Optional<Customer> user = customerRepository.findByEmail(request.getEmail());
        if (user.isPresent()){
            Optional<Loan> loan = loanRepository.findUserById(user.get().getId());
            if (loan.isPresent()){
                if (loan.get().getBalance().intValue() > 0){
                    throw new Loan24Exception("You have to pay your outstanding loan of " + loan.get().getBalance());
                }
                Loan savedLoan = getLoan(request, user.get());
                return getLoanResponse(savedLoan);
            }
            Loan saved = getLoan(request, user.get());
            return getLoanResponse(saved);
        }
        throw new InvalidUserException("Invalid user!!!");
    }

    private static LoanResponse getLoanResponse(Loan savedLoan) {
        return LoanResponse
                .builder()
                .message("Your loan request of ₦" + savedLoan.getLoanAmount() + " has been granted")
                .build();
    }

    private Loan getLoan(LoanRequest request, Customer user) {
        Loan newLoan = Loan
                .builder()
                .guarantorPhoneNumber(request.getGuarantorPhoneNumber())
                .guarantorName(request.getGuarantorName())
                .loanPurpose(request.getLoanPurpose())
                .loanAmount(request.getLoanAmount())
                .loanDuration(request.getLoanPlan())
                .balance(request.getLoanAmount())
                .dateOfLoan(LocalDate.now())
                .user(user)
                .build();
        return loanRepository.save(newLoan);
    }

    @Override
    public Customer findUser(FindUserRequest request) {
        Optional<Customer> user = customerRepository.findByEmail(request.getEmail());
        if (user.isPresent()){
            return user.get();
        }
        throw new InvalidUserException("Invalid login details!!!");
    }

    @Override
    public List<Loan> searchForLoans(String email) {
        Optional<Customer> user = customerRepository.findByEmail(email);
        if (user.isPresent()){
            List<Loan> loanHistory = loanRepository.findLoanByUserId(user.get().getId());
            if(!loanHistory.isEmpty()){
                return loanHistory;
            }
            throw new Loan24Exception("You have no loan history");
        }
        throw new InvalidUserException("Invalid login details!!!");
    }

    @Override
    public PaymentResponse makePayment(PaymentRequest request){
        Optional<Customer> user = customerRepository.findByEmail(request.getEmail());
        if (user.isPresent()){
            Optional<Loan> userLoan = loanRepository.findUserById(user.get().getId());
            if (userLoan.isPresent()){
                if (request.getAmount().intValue() > 0){
                    if (request.getAmount().intValue() <= userLoan.get().getBalance().intValue()){
                        Payment payment = Payment
                                .builder()
                                .paymentType(request.getPaymentType())
                                .amount(request.getAmount())
                                .dateOfPayment(LocalDate.now())
                                .user(user.get())
                                .loan(userLoan.get())
                                .build();
                        userLoan.get().setBalance(userLoan.get().getBalance().subtract(request.getAmount()));
                        loanRepository.save(userLoan.get());
                        @SuppressWarnings("newPayment")
                        Payment newPayment = paymentRepository.save(payment);
                        return getPaymentResponse();
                    }
                    throw new Loan24Exception("You can't pay more than you owe");
                }
                throw new Loan24Exception("Amount is invalid");
            }

        }
        throw new InvalidUserException("Invalid login details!!!");
    }

    private static PaymentResponse getPaymentResponse() {
        return PaymentResponse
                .builder()
                .message("Your payment was successful")
                .build();
    }

    @Override
    public boolean isLoanApproved() {
        return false;
    }

    @Override
    public List<Payment> paymentHistory(String email) {
        Optional<Customer> user = customerRepository.findByEmail(email);
        if (user.isPresent()){
            List<Payment> paymentHistory = paymentRepository.findPaymentByUserId(user.get().getId());
            if (!paymentHistory.isEmpty()){
                return paymentHistory;
            }
            throw new Loan24Exception("You have no payment History!!!");
        }
        throw new InvalidUserException("Invalid login details!!!");
    }
}
