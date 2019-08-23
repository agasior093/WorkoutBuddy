package com.johndoe.workoutbuddy.common.utils;

import com.johndoe.workoutbuddy.common.messages.Error;
import io.vavr.control.Either;

public class EitherUtils {
    public static Either chain(Either first, Either second, Error error) {
        return first.isRight() ? second : error.toEitherLeft();
    }
}
