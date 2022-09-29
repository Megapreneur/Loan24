package com.example.loan24.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailSenderService {
    void sendEmail(String to,String subject, String message);
}
