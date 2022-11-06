package com.example.loan24.service;

import com.example.loan24.data.model.User;
import com.example.loan24.dto.request.LoginUserRequest;
import com.example.loan24.dto.response.LoginUserResponse;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Override
    public LoginUserResponse login(LoginUserRequest request) {
        return null;
    }

    @Override
    public User getUserByUsername(String email) {
        return null;
    }
}
