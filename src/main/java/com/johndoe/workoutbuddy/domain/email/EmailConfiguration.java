package com.johndoe.workoutbuddy.domain.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfiguration {

    @Bean
    public EmailFacade emailFacade() {
        return new EmailFacade(new SendEmailUseCase(), new CreateEmailUseCase());
    }
}
