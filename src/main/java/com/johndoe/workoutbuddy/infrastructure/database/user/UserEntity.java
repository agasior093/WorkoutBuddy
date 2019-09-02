package com.johndoe.workoutbuddy.infrastructure.database.user;

import com.johndoe.workoutbuddy.infrastructure.database.BaseEntity;
import lombok.Builder;
import lombok.Getter;
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
}
