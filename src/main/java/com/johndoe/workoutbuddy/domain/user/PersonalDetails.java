package com.johndoe.workoutbuddy.domain.user;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

enum Gender {
    MALE, FEMALE
}

@Getter
@Builder
class PersonalDetails {
    private final String firstName;
    private final String lastName;
    private final Gender gender;
    private final LocalDate birthDate;
    private Double weight;
    private Double height;
}
