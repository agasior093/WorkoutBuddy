package com.johndoe.workoutbuddy.domain.user.dto;

import com.johndoe.workoutbuddy.domain.DomainError;

public enum UserError implements DomainError {
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
