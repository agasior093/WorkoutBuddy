package com.johndoe.workoutbuddy.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;


@Builder
@Getter
public class UserDto {
    private final String username;
    private final String email;
    private final String password;
    private final Set<String> roles;
    private final boolean active;
    private final String firstName;
    private final String lastName;
    private final GenderDto gender;
    private final LocalDate birthDate;
    private Double weight;
    private Double height;
}
