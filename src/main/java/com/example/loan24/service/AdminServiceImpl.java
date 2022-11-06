package com.example.loan24.service;

import com.example.loan24.data.model.Admin;
import com.example.loan24.data.repository.AdminRepository;
import com.example.loan24.dto.request.RegisterAdminRequest;
import com.example.loan24.dto.response.RegisterUserResponse;
import com.example.loan24.exception.PasswordDoesNotMatchException;
import com.example.loan24.exception.UserAlreadyExistException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper;

    @Override
    public RegisterUserResponse register(RegisterAdminRequest request) throws UserAlreadyExistException {
        if (adminRepository.existByEmail(request.getEmail())) throw new UserAlreadyExistException("Admin already exist");
        if(request.getPassword().equals(request.getConfirmPassword())){
            Admin newAdmin = modelMapper.map(request, Admin.class);
            adminRepository.save(newAdmin);
            return RegisterUserResponse.builder()
                    .message("You registration was successful")
                    .build();
        }
        throw new PasswordDoesNotMatchException("Your password does not match");
    }
}
