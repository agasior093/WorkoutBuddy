package com.johndoe.workoutbuddy.domain.email;

import com.johndoe.workoutbuddy.domain.DomainError;
import com.johndoe.workoutbuddy.domain.SuccessMessage;
import com.johndoe.workoutbuddy.domain.email.dto.UserActivationEmail;
import io.vavr.control.Either;
import lombok.extern.java.Log;

@Log
public class EmailFacade {
    private final EmailService sendEmail;

    EmailFacade(com.johndoe.workoutbuddy.domain.email.port.EmailSender sender){
        sendEmail = new EmailService(sender);
    }

    public Either<DomainError, SuccessMessage> sendUserVerificationEmail(UserActivationEmail userActivationEmail) {
        return sendEmail.sendEmail(userActivationEmail);
    }

}
