package com.example.loan24.Advice;

import com.example.loan24.exception.Loan24Exception;
import com.example.loan24.exception.UserAlreadyExistException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistException.class)
    public Map<String, String> handleUserException(UserAlreadyExistException e){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error message is ", e.getMessage());
        return errorMap;
    }

    @ExceptionHandler(Loan24Exception.class)
    public Map<String, String> handleLoan24Exception(Loan24Exception e){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error message is ", e.getMessage());
        return errorMap;
    }
}
