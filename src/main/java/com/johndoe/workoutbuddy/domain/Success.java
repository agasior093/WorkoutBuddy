package com.johndoe.workoutbuddy.domain;

import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Success {
    private String message;
    public Either<Error, Success> toEitherRight() {
        return Either.right(this);
    }
}
