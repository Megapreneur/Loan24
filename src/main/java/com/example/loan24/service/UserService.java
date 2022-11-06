package com.example.loan24.service;

import com.example.loan24.data.model.User;
import com.example.loan24.dto.request.LoginUserRequest;
import com.example.loan24.dto.response.LoginUserResponse;

public interface UserService {
    LoginUserResponse login(LoginUserRequest request);
    User getUserByUsername(String email);

}
