package com.johndoe.workoutbuddy.domain.email;

import com.johndoe.workoutbuddy.common.messages.Error;
import com.johndoe.workoutbuddy.common.messages.Success;
import com.johndoe.workoutbuddy.domain.email.model.UserActivationEmail;
import com.johndoe.workoutbuddy.domain.email.dto.error.EmailError;
import com.johndoe.workoutbuddy.domain.email.model.EmailMessage;
import com.johndoe.workoutbuddy.domain.email.port.EmailSender;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
class EmailService {
    private final EmailSender emailSender;

    Either<Error, Success> sendEmail(String username, String receiver, String token) {
        var email = prepareEmail(username, receiver, token);
        return Try.of(() -> tryToSend(email))
                .onFailure(e -> log.error(e.getMessage()))
                .toEither(EmailError.SENDING_FAILED);
    }

    private Success tryToSend(EmailMessage email) throws RuntimeException {
        emailSender.sendEmail(email);
        return new Success("Activation email successfully sent to " + email.getReceiver());
    }

    private UserActivationEmail prepareEmail(String username, String receiver, String token) {
        return UserActivationEmail.builder()
                .token(token)
                .username(username)
                .receiver(receiver)
                .build();
    }
}
