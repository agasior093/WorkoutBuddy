package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.common.messages.Error;
import com.johndoe.workoutbuddy.domain.user.dto.CreateUserDto;
import com.johndoe.workoutbuddy.domain.user.dto.error.UserError;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;

@RequiredArgsConstructor
class UserValidator {
    private final UserRepository userRepository;

    Either<Error, CreateUserDto> validate(CreateUserDto userDto) {
        if (!validEmail(userDto.getEmail())) return Either.left(UserError.INVALID_EMAIL);
        if (userRepository.findByUsername(userDto.getUsername()).isPresent())
            return Either.left(UserError.USERNAME_ALREADY_EXISTS);
        if (userRepository.findByEmail(userDto.getEmail()).isPresent())
            return Either.left(UserError.EMAIL_ALREADY_EXISTS);
        return Either.right(userDto);
    }

    private static Boolean validEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
