package com.johndoe.workoutbuddy.domain.user.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;


@Builder
@Getter
public class User {
    private final String id;
    private final String username;
    private final String email;
    private final String password;
    private final Set<String> roles;
    private final boolean active;
    private final String firstName;
    private final String lastName;
    private final Gender gender;
    private final LocalDate birthDate;
    private final Double weight;
    private final Double height;
}
