package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.domain.DomainError;
import com.johndoe.workoutbuddy.domain.SuccessMessage;
import com.johndoe.workoutbuddy.domain.email.EmailFacade;
import com.johndoe.workoutbuddy.domain.email.dto.UserActivationEmail;
import com.johndoe.workoutbuddy.domain.user.dto.RegisterUserDto;
import com.johndoe.workoutbuddy.domain.user.dto.UserError;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import com.johndoe.workoutbuddy.domain.user.port.ActivationTokenRepository;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
class UserCreator {
    private final UserRepository userRepository;
    private final ActivationTokenRepository tokenRepository;
    private final EmailFacade emailFacade;
    private final ObjectMapper objectMapper;

    Either<DomainError, SuccessMessage> createUser(RegisterUserDto registerUserDto) {
        var validationError = validate(registerUserDto);
        if(validationError.isPresent()) return Either.left(validationError.get());
        var user = User.createUser(registerUserDto);
        var savedUser = user.map(this::saveUser);
        var emailDto = savedUser.map(this::createActivationEmail);
        return emailDto.isRight() ? emailFacade.sendActivationEmail(emailDto.get())
                : Either.left(emailDto.getLeft());
    }

    private User saveUser(User user) {
        userRepository.saveUser(objectMapper.userToDto(user));
        return user;
    }

    private UserActivationEmail createActivationEmail(User user) {
        final UUID uuid = tokenRepository.generateToken(user.getUsername());
        return UserActivationEmail.builder()
                .token(uuid)
                .username(user.getUsername())
                .receiver(user.getEmail())
                .build();
    }

    private Optional<DomainError> validate(RegisterUserDto userDto) {
        if(userRepository.findByUsername(userDto.getUsername()).isPresent()) return Optional.of(UserError.USERNAME_ALREADY_EXISTS);
        if(userRepository.findByEmail(userDto.getEmail()).isPresent()) return Optional.of(UserError.EMAIL_ALREADY_EXISTS);
        return Optional.empty();
    }

}
