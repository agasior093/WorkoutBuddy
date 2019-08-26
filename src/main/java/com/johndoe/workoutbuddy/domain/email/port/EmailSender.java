package com.johndoe.workoutbuddy.domain.email.port;


import com.johndoe.workoutbuddy.domain.email.model.EmailMessage;

public interface EmailSender {
    void sendEmail(EmailMessage emailMessage) throws RuntimeException;
}
