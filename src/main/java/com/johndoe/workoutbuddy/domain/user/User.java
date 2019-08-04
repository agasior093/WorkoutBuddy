package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.domain.DomainError;
import com.johndoe.workoutbuddy.domain.user.dto.PersonalDetailsDto;
import com.johndoe.workoutbuddy.domain.user.dto.UserDto;
import com.johndoe.workoutbuddy.domain.user.dto.UserError;
import com.johndoe.workoutbuddy.domain.user.dto.RegisterUserDto;
import io.vavr.control.Either;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.validator.routines.EmailValidator;

@Getter
@Builder
class User {
    private final String username;
    private String email;
    private String password;
    private String[] roles;
    private boolean active;
    private PersonalDetails personalDetails;

    static Either<DomainError, User> createUser(RegisterUserDto dto) {
        return validEmail(dto.getEmail()) ?
            Either.right(User.builder()
                    .username(dto.getUsername())
                    .password(dto.getPassword())
                    .email(dto.getEmail())
                    .active(false)
                    .roles(new String[] {"USER"})
                    .build()) :
            Either.left(UserError.INVALID_EMAIL);
    }

    void activate() {
        this.active = true;
    }

    private User(String username, String email, String password, String[] roles, boolean active, PersonalDetails personalDetails) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.active = active;
        this.personalDetails = personalDetails;
    }

    private static boolean validEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
