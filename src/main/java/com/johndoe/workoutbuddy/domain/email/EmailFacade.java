package com.johndoe.workoutbuddy.domain.email;

import com.johndoe.workoutbuddy.domain.DomainError;
import com.johndoe.workoutbuddy.domain.SuccessMessage;
import com.johndoe.workoutbuddy.domain.email.dto.VerificationEmail;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailFacade {
    private final SendEmailUseCase sendEmail;

    public Either<DomainError, SuccessMessage> sendUserVerificationEmail(VerificationEmail verificationEmail) {
        return sendEmail.send(verificationEmail);
    }

}
