package com.johndoe.workoutbuddy.configuration.bean;

import com.johndoe.workoutbuddy.adapter.repository.InMemoryUserRepository;
import com.johndoe.workoutbuddy.adapter.repository.InMemoryVerificationTokenRepository;
import com.johndoe.workoutbuddy.domain.user.port.VerificationTokenRepository;
import com.johndoe.workoutbuddy.domain.user.usecase.ReadUser;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import com.johndoe.workoutbuddy.domain.user.usecase.RegisterUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserConfiguration {

    private final UserRepository repository = new InMemoryUserRepository();
    private final VerificationTokenRepository verificationTokenRepository = new InMemoryVerificationTokenRepository();

    @Bean
    ReadUser userReader() {
        return new ReadUser(repository);
    }

    @Bean
    RegisterUser registerUser() {
        return new RegisterUser(repository, verificationTokenRepository);
    }
}
