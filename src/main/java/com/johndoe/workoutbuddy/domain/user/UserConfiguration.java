package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.domain.email.EmailFacade;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserConfiguration {
    @Bean
    UserFacade userFacade(UserRepository repository, EmailFacade emailFacade) {
        return new UserFacade(repository, emailFacade);
    }
}
