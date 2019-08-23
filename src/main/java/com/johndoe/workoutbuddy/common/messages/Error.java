package com.johndoe.workoutbuddy.common.messages;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.vavr.control.Either;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public interface Error {
    String getCause();
    default Either toEitherLeft() {
        return Either.left(this);
    }
}
