package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.domain.user.dto.RegisterUserDto;
import com.johndoe.workoutbuddy.domain.user.dto.UserError;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import com.johndoe.workoutbuddy.domain.user.port.VerificationTokenRepository;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
class RegisterUserUseCase {
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;

    Either<UserError, UUID> register(RegisterUserDto registerUserDto) {
        if(userRepository.findUser(registerUserDto.getUsername()).isPresent())
            return Either.left(UserError.USERNAME_ALREADY_EXISTS);
        var userCreationResult = User.createUser(registerUserDto);
        return userCreationResult
            .map(user -> {
                userRepository.save(user.toDto());
                return verificationTokenRepository.generateVerificationToken(user.getUsername());
            });
    }
}
