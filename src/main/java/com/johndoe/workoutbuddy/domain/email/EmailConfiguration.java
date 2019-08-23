package com.johndoe.workoutbuddy.domain.email;

import com.johndoe.workoutbuddy.domain.email.port.EmailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class EmailConfiguration {
    @Bean
    EmailFacade emailFacade(EmailSender emailSender) {
        return new EmailFacade(emailSender);
    }
}
