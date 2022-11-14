package com.example.loan24.service;

import com.example.loan24.data.model.Admin;
import com.example.loan24.data.model.Customer;
import com.example.loan24.data.model.User;
import com.example.loan24.data.repository.AdminRepository;
import com.example.loan24.data.repository.CustomerRepository;
import com.example.loan24.dto.request.LoginUserRequest;
import com.example.loan24.dto.response.LoginUserResponse;
import com.example.loan24.exception.InvalidUserException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;

    @Override
    public LoginUserResponse login(LoginUserRequest request) {
        Optional<Admin> admin = adminRepository.findByEmail(request.getEmail());
        if (admin.isPresent() && admin.get().getPassword().equals(request.getPassword())) return response(admin.get());
        Optional<Customer> customer = customerRepository.findByEmail(request.getEmail());
        if (customer.isPresent() && customer.get().getPassword().equals(request.getPassword()))return response(customer.get());
        throw new InvalidUserException("Invalid Details!!!");
    }

    private LoginUserResponse response(User user) {
        return LoginUserResponse.builder()
                .message(user.getName()+ " , you are logged in successfully")
                .build();
    }

    @Override
    public User getUserByUsername(String email) {
        Optional<Admin> admin = adminRepository.findByEmail(email);
        if (admin.isPresent()) return admin.get();

        Optional<Customer> customer = customerRepository.findByEmail(email);
        if (customer.isPresent()) return customer.get();

        throw new UsernameNotFoundException("User not found");
    }
}
