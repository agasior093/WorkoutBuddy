package com.johndoe.workoutbuddy.domain.email.port;


import com.johndoe.workoutbuddy.domain.email.dto.EmailMessage;

public interface EmailSender {
    void sendEmail(EmailMessage emailMessage) throws RuntimeException;
}
