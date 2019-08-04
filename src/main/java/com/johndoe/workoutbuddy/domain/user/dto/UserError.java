package com.johndoe.workoutbuddy.domain.user.dto;

import com.johndoe.workoutbuddy.domain.Error;

public enum UserError implements Error {
    USERNAME_ALREADY_EXISTS("Username already exists"),
    INVALID_EMAIL("Provided email address is invalid");

    private final String cause;
    UserError(String cause) {
        this.cause = cause;
    }
    @Override
    public String getCause() {
        return null;
    }
}
