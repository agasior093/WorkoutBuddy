package com.johndoe.workoutbuddy.common.messages;

import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Success {
    private String message;
    public Either toEitherRight() {
        return Either.right(this);
    }
}
