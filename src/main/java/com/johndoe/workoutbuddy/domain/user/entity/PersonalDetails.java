package com.johndoe.workoutbuddy.domain.user.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
public class PersonalDetails {
    private final String firstName;
    private final String lastName;
    private final Gender gender;
    private final LocalDate birthDate;
    private final Double weight;
    private final Double height;
}
