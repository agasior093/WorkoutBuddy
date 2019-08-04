package com.johndoe.workoutbuddy.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class UserDto {
    private final String username;
    private final String email;
    private final String password;
    private final String[] roles;
    private final boolean active;
    private final PersonalDetailsDto personalDetails;
}
