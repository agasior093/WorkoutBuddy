package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.domain.DomainError;
import com.johndoe.workoutbuddy.domain.user.dto.RegisterUserDto;
import com.johndoe.workoutbuddy.domain.user.dto.UserError;
import io.vavr.collection.CharSeq;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Validation;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.validator.routines.EmailValidator;

import java.time.LocalDate;
import java.util.Set;

enum Gender {
    MALE, FEMALE
}

@Getter
@Builder
class User {
    private final String username;
    private String email;
    private String password;
    private Set<String> roles;
    private boolean active;
    private final String firstName;
    private final String lastName;
    private final Gender gender;
    private final LocalDate birthDate;
    private Double weight;
    private Double height;

    static Either<DomainError, User> createUser(RegisterUserDto dto) {
        return validEmail(dto.getEmail()) ?
            Either.right(User.builder()
                    .username(dto.getUsername())
                    .password(dto.getPassword())
                    .email(dto.getEmail())
                    .active(false)
                    .roles(Set.of("USER"))
                    .build()) :
            Either.left(UserError.INVALID_EMAIL);
    }

    void activate() {
        this.active = true;
    }

    private static Boolean validEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

}
