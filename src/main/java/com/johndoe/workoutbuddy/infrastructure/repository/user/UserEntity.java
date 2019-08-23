package com.johndoe.workoutbuddy.infrastructure.repository.user;

import com.johndoe.workoutbuddy.infrastructure.repository.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Builder
@Document(collection = "user")
class UserEntity extends BaseEntity<String> {
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
