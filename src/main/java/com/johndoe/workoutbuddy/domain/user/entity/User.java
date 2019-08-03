package com.johndoe.workoutbuddy.domain.user.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;

@Getter
@RequiredArgsConstructor
@Builder
public class User {
    private final String username;
    private final String password;
    private final String[] roles;
}
