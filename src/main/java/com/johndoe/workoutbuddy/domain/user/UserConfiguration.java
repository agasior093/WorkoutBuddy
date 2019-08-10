package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.adapter.repository.inmemory.InMemoryActivationTokenRepository;
import com.johndoe.workoutbuddy.adapter.repository.inmemory.InMemoryUserRepository;
import com.johndoe.workoutbuddy.domain.email.EmailFacade;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import com.johndoe.workoutbuddy.domain.user.port.ActivationTokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserConfiguration {
    @Bean
    UserFacade userFacade(UserRepository userRepository, ActivationTokenRepository tokenRepository, EmailFacade emailFacade) {
        return new UserFacade(userRepository, tokenRepository, emailFacade);
    }

    UserFacade userFacade(InMemoryUserRepository userRepository, InMemoryActivationTokenRepository tokenRepository, EmailFacade emailFacade) {
        return new UserFacade(userRepository, tokenRepository, emailFacade);
    }
}
