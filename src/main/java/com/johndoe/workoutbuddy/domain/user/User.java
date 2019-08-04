package com.johndoe.workoutbuddy.domain.user;

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
    private final String email;
    private final String password;
    private final String[] roles;
    private final boolean active;
    private final PersonalDetails personalDetails;

    private User(String username, String email, String password, String[] roles, boolean active, PersonalDetails personalDetails) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.active = active;
        this.personalDetails = personalDetails;
    }

    static Either<UserError, User> createUser(RegisterUserDto dto) {
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

    UserDto toDto() {
        return UserDto.builder()
                .username(this.username)
                .password(this.password)
                .email(this.email)
                .roles(this.roles)
                .active(this.active)
                .personalDetails(this.personalDetails.toDto())
                .build();
    }

    static User fromDto(UserDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .roles(dto.getRoles())
                .active(dto.isActive())
                .personalDetails(PersonalDetails.fromDto(dto.getPersonalDetails()))
                .build();
    }

    private static boolean validEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
