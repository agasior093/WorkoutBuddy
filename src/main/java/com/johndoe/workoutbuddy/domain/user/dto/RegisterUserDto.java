package com.johndoe.workoutbuddy.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterUserDto {
    private final String username;
    private final String password;
    private final String email;
}
