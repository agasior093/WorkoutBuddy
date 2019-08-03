package com.johndoe.workoutbuddy.domain.user.entity;

public enum Gender {
    MALE("Mężczyzna"), FEMALE("Kobieta");
    private final String value;
    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
