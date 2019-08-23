package com.johndoe.workoutbuddy.infrastructure.repository.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Set;

enum GenderEntity {
    MALE, FEMALE
}

@Getter
@Builder
@Document(collection = "user")
public class UserEntity {
    @Id
    private String id;
    private final String username;
    private final String email;
    private final String password;
    private final Set<String> roles;
    private final boolean active;
    private final String firstName;
    private final String lastName;
    private final GenderEntity gender;
    private final LocalDate birthDate;
    private final Double weight;
    private final Double height;
}
