package com.johndoe.workoutbuddy.domain.email;

import com.johndoe.workoutbuddy.domain.DomainError;
import com.johndoe.workoutbuddy.domain.SuccessMessage;
import com.johndoe.workoutbuddy.domain.email.dto.error.EmailError;
import com.johndoe.workoutbuddy.domain.email.dto.EmailMessage;
import com.johndoe.workoutbuddy.domain.email.port.EmailSender;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor
class SendEmailUseCase {
    private final EmailSender emailSender;

    Either<DomainError, SuccessMessage> send(EmailMessage message) {
        return Try.of(() -> sendEmail(message))
                .onFailure(e -> log.severe(e.getMessage()))
                .toEither(EmailError.SENDING_FAILED);
    }

    private SuccessMessage sendEmail(EmailMessage message) throws RuntimeException {
        emailSender.sendEmail(message);
        return new SuccessMessage("Email successfully sent to " + message.getReceiver());
    }
}
