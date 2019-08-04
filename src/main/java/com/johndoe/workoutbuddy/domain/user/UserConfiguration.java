package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.adapter.repository.InMemoryUserRepository;
import com.johndoe.workoutbuddy.adapter.repository.InMemoryVerificationTokenRepository;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import com.johndoe.workoutbuddy.domain.user.port.VerificationTokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserConfiguration {
    private final UserRepository userRepository = new InMemoryUserRepository();
    private final VerificationTokenRepository verificationTokenRepository = new InMemoryVerificationTokenRepository();

    @Bean
    UserFacade userFacade() {
        ReadUserUseCase readUser = new ReadUserUseCase(userRepository);
        RegisterUserUseCase registerUser = new RegisterUserUseCase(userRepository, verificationTokenRepository);
        return new UserFacade(readUser, registerUser);
    }
}
