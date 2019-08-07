package com.johndoe.workoutbuddy.domain.email;

import com.johndoe.workoutbuddy.domain.DomainError;
import com.johndoe.workoutbuddy.domain.SuccessMessage;
import com.johndoe.workoutbuddy.domain.email.dto.VerificationEmail;
import com.johndoe.workoutbuddy.domain.email.port.EmailSender;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
public class EmailFacade {
    private final SendEmailUseCase sendEmail;

    public EmailFacade(EmailSender sender){
        sendEmail = new SendEmailUseCase(sender);
    }

    public Either<DomainError, SuccessMessage> sendUserVerificationEmail(VerificationEmail verificationEmail) {
        var result = sendEmail.send(verificationEmail);
        log.info(result.toString());
        return result;
    }

}
