package com.johndoe.workoutbuddy.domain.user.dto;

import com.johndoe.workoutbuddy.domain.DomainError;

public enum UserError implements DomainError {
    USERNAME_ALREADY_EXISTS("Username already exists"),
    INVALID_EMAIL("Provided email address is invalid"),
    EXPIRED_REGISTRATION_TOKEN("Registration token has expired or has already been used"),
    ACTIVATION_FAILED("Activation of user failed");

    private final String cause;
    UserError(String cause) {
        this.cause = cause;
    }
    @Override
    public String getCause() {
        return null;
    }
}
