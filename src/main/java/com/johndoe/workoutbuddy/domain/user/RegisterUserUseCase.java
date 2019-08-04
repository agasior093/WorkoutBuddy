package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.domain.DomainError;
import com.johndoe.workoutbuddy.domain.SuccessMessage;
import com.johndoe.workoutbuddy.domain.email.EmailFacade;
import com.johndoe.workoutbuddy.domain.email.dto.EmailError;
import com.johndoe.workoutbuddy.domain.email.dto.EmailMessageDto;
import com.johndoe.workoutbuddy.domain.email.dto.VerificationEmailDto;
import com.johndoe.workoutbuddy.domain.user.dto.RegisterUserDto;
import com.johndoe.workoutbuddy.domain.user.dto.UserDto;
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
    private final EmailFacade emailFacade;

    Either<DomainError, SuccessMessage> register(RegisterUserDto registerUserDto) {
        if(userRepository.findUser(registerUserDto.getUsername()).isPresent())
            return Either.left(UserError.USERNAME_ALREADY_EXISTS);
        var userCreationResult = User.createUser(registerUserDto);
        var emailDto = userCreationResult
                .map(this::persistUser)
                .map(this::createVerificationEmail);
        return emailDto.isRight() ? emailFacade.sendUserVerificationEmail(emailDto.get())
                : Either.left(emailDto.getLeft());
    }

    private User persistUser(User user) {
        userRepository.save(user.toDto());
        return user;
    }

    private VerificationEmailDto createVerificationEmail(User user) {
        final UUID uuid = verificationTokenRepository.generateVerificationToken(user.getUsername());
        return new VerificationEmailDto(uuid, user.getEmail());
    }

}
