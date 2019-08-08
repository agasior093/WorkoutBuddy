package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.domain.email.EmailFacade;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import com.johndoe.workoutbuddy.domain.user.port.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserConfiguration {
    @Bean
    UserFacade userFacade(UserRepository userRepository, VerificationTokenRepository tokenRepository, EmailFacade emailFacade) {
        return new UserFacade(userRepository, tokenRepository, emailFacade);
    }
}
