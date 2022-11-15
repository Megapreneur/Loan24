package com.example.loan24.service;

import com.example.loan24.dto.request.EmailNotificationRequest;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationServiceImpl implements EmailNotificationService{
    @Override
    public String sendHtmlMail(EmailNotificationRequest notificationRequest) {
        return null;
    }
}
