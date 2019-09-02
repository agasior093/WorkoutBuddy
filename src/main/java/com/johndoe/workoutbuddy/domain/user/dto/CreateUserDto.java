package com.johndoe.workoutbuddy.domain.user.dto;

import com.johndoe.workoutbuddy.domain.user.model.Gender;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserDto {
    private final String username;
    private final String password;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String gender;
    private final String birthDate;
}
