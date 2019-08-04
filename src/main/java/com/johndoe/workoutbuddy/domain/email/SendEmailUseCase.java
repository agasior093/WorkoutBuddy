package com.johndoe.workoutbuddy.domain.email;

import com.johndoe.workoutbuddy.domain.DomainError;
import com.johndoe.workoutbuddy.domain.email.dto.EmailError;
import com.johndoe.workoutbuddy.domain.SuccessMessage;
import io.vavr.control.Either;
import lombok.extern.java.Log;

@Log
class SendEmailUseCase {
    Either<DomainError, SuccessMessage> send(EmailMessage message) {
        if(message == null) return Either.left(EmailError.EMPTY_MESSAGE);
        return Either.right(new SuccessMessage("Email sent"));
    }
}
