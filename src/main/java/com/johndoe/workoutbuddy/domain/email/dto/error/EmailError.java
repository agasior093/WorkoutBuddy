package com.johndoe.workoutbuddy.domain.email.dto.error;

import com.johndoe.workoutbuddy.domain.Error;

public enum EmailError implements Error {
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
