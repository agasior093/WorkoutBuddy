package com.johndoe.workoutbuddy.domain.user.usecase;

import com.johndoe.workoutbuddy.domain.user.dto.UserError;
import com.johndoe.workoutbuddy.domain.user.dto.UserRegisterDto;
import com.johndoe.workoutbuddy.domain.user.entity.User;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import com.johndoe.workoutbuddy.domain.user.port.VerificationTokenRepository;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class RegisterUser {

    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;

    public Either<UserError, UUID> register(UserRegisterDto userRegisterDto) {
        if(userRepository.findUser(userRegisterDto.getUsername()).isPresent())
            return Either.left(UserError.USERNAME_ALREADY_EXISTS);

        var userCreationResult = User.createUser(userRegisterDto);
        return userCreationResult
                .map(user -> {
                    userRepository.save(user);
                    return verificationTokenRepository.generateVerificationToken(user.getUsername());
                });
    }
}
