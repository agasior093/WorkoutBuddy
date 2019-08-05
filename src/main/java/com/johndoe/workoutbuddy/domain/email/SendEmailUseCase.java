package com.johndoe.workoutbuddy.domain.email;

import com.johndoe.workoutbuddy.domain.DomainError;
import com.johndoe.workoutbuddy.domain.SuccessMessage;
import com.johndoe.workoutbuddy.domain.email.dto.EmailMessage;
import com.johndoe.workoutbuddy.domain.email.dto.error.EmailError;
import com.johndoe.workoutbuddy.domain.email.port.EmailSender;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@RequiredArgsConstructor
@Log
class SendEmailUseCase {
    private final EmailSender emailSender;

    Either<DomainError, SuccessMessage> send(EmailMessage message) {
        try {
            emailSender.sendEmail(message);
        } catch (Exception e) {
            log.severe(e.getMessage());
            return Either.left(EmailError.SENDING_FAILED);
        }
        return Either.right(new SuccessMessage("Email sent"));
    }
}
