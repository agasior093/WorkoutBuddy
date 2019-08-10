package com.johndoe.workoutbuddy.domain.user.dto;

import com.johndoe.workoutbuddy.domain.common.Error;

public enum UserError implements Error {
    USERNAME_ALREADY_EXISTS("Username already exists"),
    EMAIL_ALREADY_EXISTS("Email already exists"),
    INVALID_EMAIL("Provided email address is invalid"),
    EXPIRED_ACTIVATION_TOKEN("Activation token has expired or has already been used"),
    ACTIVATION_TOKEN_NOT_FOUND("Activation token not found"),
    USER_NOT_FOUND("User not found"),
    ACTIVATION_FAILED("Activation of user failed"),
    PERSISTENCE_FAILED("");



    private final String cause;
    UserError(String cause) {
        this.cause = cause;
    }
    @Override
    public String getCause() {
        return cause;
    }
}
