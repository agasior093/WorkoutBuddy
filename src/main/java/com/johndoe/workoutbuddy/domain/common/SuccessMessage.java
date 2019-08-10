package com.johndoe.workoutbuddy.domain.common;

import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SuccessMessage {
    private String message;
    public Either<DomainError, SuccessMessage> toEitherRight() {
        return Either.right(this);
    }
}
