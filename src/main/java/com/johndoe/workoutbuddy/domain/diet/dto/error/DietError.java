package com.johndoe.workoutbuddy.domain.diet.dto.error;

import com.johndoe.workoutbuddy.common.messages.Error;

public enum DietError implements Error {

    PERSISTENCE_FAILED;

    @Override
    public String getCause() {
        return null;
    }
}
