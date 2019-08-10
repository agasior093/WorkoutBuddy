package com.johndoe.workoutbuddy.domain.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.vavr.control.Either;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public interface DomainError {
    String getCause();
    default Either<DomainError, SuccessMessage> toEitherLeft() {
        return Either.left(this);
    }
}
