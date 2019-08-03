package com.johndoe.workoutbuddy.configuration;

import com.johndoe.workoutbuddy.adapter.repository.InMemoryUserRepository;
import com.johndoe.workoutbuddy.domain.user.UserReader;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
class UserConfiguration {

    private final UserRepository repository = new InMemoryUserRepository();

    @Bean
    UserReader userReader() {
        return new UserReader(repository);
    }

}
