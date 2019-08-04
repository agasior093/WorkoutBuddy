package com.johndoe.workoutbuddy.domain.email;

import com.johndoe.workoutbuddy.domain.DomainError;
import com.johndoe.workoutbuddy.domain.SuccessMessage;
import com.johndoe.workoutbuddy.domain.email.dto.EmailError;
import com.johndoe.workoutbuddy.domain.email.dto.VerificationEmailDto;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class EmailFacade {
    private final SendEmailUseCase sendEmail;
    private final CreateEmailUseCase createEmail;

    public Either<DomainError, SuccessMessage> sendUserVerificationEmail(VerificationEmailDto dto) {
        return sendEmail.send(createEmail.createUserVerificationEmail(dto.getUuid(), dto.getReceiver()));
    }
}
