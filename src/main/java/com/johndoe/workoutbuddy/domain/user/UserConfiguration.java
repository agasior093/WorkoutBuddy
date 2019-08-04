package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.adapter.repository.InMemoryUserRepository;
import com.johndoe.workoutbuddy.adapter.repository.InMemoryVerificationTokenRepository;
import com.johndoe.workoutbuddy.domain.email.EmailConfiguration;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import com.johndoe.workoutbuddy.domain.user.port.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UserConfiguration {
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final EmailConfiguration emailConfiguration;

    @Bean
    public UserFacade userFacade() {
        ReadUserUseCase readUser = new ReadUserUseCase(userRepository);
        RegisterUserUseCase registerUser =
                new RegisterUserUseCase(userRepository, verificationTokenRepository, emailConfiguration.emailFacade());
        return new UserFacade(readUser, registerUser);
    }
}
