package com.example.loan24.Service;

import com.example.loan24.dto.request.RegisterUserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class Loan24ServiceImplTest {

    @Test
    public void testThatAUserCanBeCreated(){
        RegisterUserRequest request = RegisterUserRequest
                .builder()
                .name("Thomas Uche")
                .accountNumber("0176644498")
                .address("11, borno way")
                .email("tu@gmail.com")
                .guarantorName("Jeremiah ").build();
    }

}