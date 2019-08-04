package com.johndoe.workoutbuddy.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PersonalDetailsDto {
    private final String firstName;
    private final String lastName;
    private final String gender;
    private final String birthDate;
    private final Double weight;
    private final Double height;
}
