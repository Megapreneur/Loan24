package com.example.loan24.service;

import com.example.loan24.dto.request.EmailNotificationRequest;

public interface EmailNotificationService {
    String sendHtmlMail(EmailNotificationRequest notificationRequest);
}
