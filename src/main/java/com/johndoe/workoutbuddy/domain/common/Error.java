package com.johndoe.workoutbuddy.domain.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.vavr.control.Either;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public interface Error {
    String getCause();
    default Either<Error, Success> toEitherLeft() {
        return Either.left(this);
    }
}
