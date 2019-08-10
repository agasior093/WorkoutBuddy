package com.johndoe.workoutbuddy.domain.email;

import com.johndoe.workoutbuddy.domain.common.DomainError;
import com.johndoe.workoutbuddy.domain.common.SuccessMessage;
import com.johndoe.workoutbuddy.domain.email.port.EmailSender;
import io.vavr.control.Either;
import lombok.extern.java.Log;

@Log
public class EmailFacade {
    private final EmailService emailService;

    EmailFacade(EmailSender sender){
        emailService = new EmailService(sender);
    }

    public Either<DomainError, SuccessMessage> sendActivationEmail(String username, String receiver, String token) {
        return emailService.sendEmail(username, receiver, token);
    }
}
