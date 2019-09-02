package com.johndoe.workoutbuddy.domain.user.model;

import java.util.Optional;

public enum Gender {
    MALE, FEMALE;

    public static Gender fromString(String value) {
        return "male".equals(value) ? Gender.MALE : Gender.FEMALE;

    }
}
