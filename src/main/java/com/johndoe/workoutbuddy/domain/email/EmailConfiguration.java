package com.johndoe.workoutbuddy.domain.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class EmailConfiguration {

    @Bean
    EmailFacade emailFacade() {
        return new EmailFacade(new SendEmailUseCase(), new CreateEmailUseCase());
    }
}
