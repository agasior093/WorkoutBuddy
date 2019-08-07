package com.johndoe.workoutbuddy.domain.email;

import com.johndoe.workoutbuddy.domain.email.dto.EmailMessage;
import com.johndoe.workoutbuddy.domain.email.port.EmailSender;

public class FakeEmailSender implements EmailSender {
    @Override
    public void sendEmail(EmailMessage emailMessage) throws RuntimeException {

    }
}
