package com.johndoe.workoutbuddy.domain.email;

import com.johndoe.workoutbuddy.domain.DomainError;
import com.johndoe.workoutbuddy.domain.SuccessMessage;
import com.johndoe.workoutbuddy.domain.email.dto.UserActivationEmail;
import com.johndoe.workoutbuddy.domain.email.port.EmailSender;
import io.vavr.control.Either;
import lombok.extern.java.Log;

@Log
public class EmailFacade {
    private final EmailService emailService;

    EmailFacade(EmailSender sender){
        emailService = new EmailService(sender);
    }

    public Either<DomainError, SuccessMessage> sendActivationEmail(UserActivationEmail userActivationEmail) {
        return emailService.sendEmail(userActivationEmail);
    }
}
