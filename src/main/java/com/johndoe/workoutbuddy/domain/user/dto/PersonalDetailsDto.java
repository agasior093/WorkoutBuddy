package com.johndoe.workoutbuddy.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;


@Getter
@Builder
public class PersonalDetailsDto {
    private final String firstName;
    private final String lastName;
    private final GenderDto gender;
    private final LocalDate birthDate;
    private final Double weight;
    private final Double height;
}
