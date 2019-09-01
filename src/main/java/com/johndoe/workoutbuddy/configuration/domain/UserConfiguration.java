package com.johndoe.workoutbuddy.configuration.domain;

import com.johndoe.workoutbuddy.domain.user.UserFacade;
import com.johndoe.workoutbuddy.infrastructure.database.user.InMemoryActivationTokenRepository;
import com.johndoe.workoutbuddy.infrastructure.database.user.InMemoryUserRepository;
import com.johndoe.workoutbuddy.domain.email.EmailFacade;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import com.johndoe.workoutbuddy.domain.user.port.ActivationTokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {
    @Bean
    public UserFacade userFacade(UserRepository userRepository, ActivationTokenRepository tokenRepository, EmailFacade emailFacade) {
        return new UserFacade(userRepository, tokenRepository, emailFacade);
    }

    UserFacade userFacade(InMemoryUserRepository userRepository, InMemoryActivationTokenRepository tokenRepository, EmailFacade emailFacade) {
        return new UserFacade(userRepository, tokenRepository, emailFacade);
    }
}
