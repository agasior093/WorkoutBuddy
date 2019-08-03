package com.johndoe.workoutbuddy.domain.user.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
    private final String username;
    private final String email;
    private final String password;
    private final String[] roles;
    private final boolean active;
    private final PersonalDetails personalDetails;
}
