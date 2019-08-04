package com.johndoe.workoutbuddy.domain.email.dto;

import com.johndoe.workoutbuddy.domain.DomainError;

public enum EmailError implements DomainError {
    EMPTY_MESSAGE("Message you are trying to send is empty");

    private final String cause;
    EmailError(String cause) {
        this.cause = cause;
    }

    @Override
    public String getCause() {
        return cause;
    }
}
