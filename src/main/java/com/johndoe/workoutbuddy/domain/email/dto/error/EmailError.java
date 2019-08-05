package com.johndoe.workoutbuddy.domain.email.dto.error;

import com.johndoe.workoutbuddy.domain.DomainError;

public enum EmailError implements DomainError {
    SENDING_FAILED("Failed to send email message.");

    private final String cause;
    EmailError(String cause) {
        this.cause = cause;
    }

    @Override
    public String getCause() {
        return cause;
    }
}
