package com.johndoe.workoutbuddy.configuration.domain;

import com.johndoe.workoutbuddy.domain.email.EmailFacade;
import com.johndoe.workoutbuddy.domain.email.port.EmailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfiguration {
    @Bean
    EmailFacade emailFacade(EmailSender emailSender) {
        return new EmailFacade(emailSender);
    }
}
