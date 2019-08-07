package com.johndoe.workoutbuddy.domain.email;

import com.johndoe.workoutbuddy.adapter.email.EmailSenderImpl;
import com.johndoe.workoutbuddy.domain.email.port.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class EmailConfiguration {
    @Bean
    EmailFacade emailFacade(EmailSender emailSender) {
        return new EmailFacade(emailSender);
    }
}
