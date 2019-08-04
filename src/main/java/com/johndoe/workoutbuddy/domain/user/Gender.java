package com.johndoe.workoutbuddy.domain.user;

enum Gender {
    MALE("Mężczyzna"), FEMALE("Kobieta");
    private final String value;
    Gender(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

    static Gender fromString(String value) {
        if(value == null) return null;
        return value.equals("Mężczyzna") ? Gender.MALE : Gender.FEMALE;
    }
}
