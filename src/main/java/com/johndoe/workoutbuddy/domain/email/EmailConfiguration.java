package com.johndoe.workoutbuddy.domain.email;

import com.johndoe.workoutbuddy.adapter.email.EmailSenderImpl;
import com.johndoe.workoutbuddy.domain.email.port.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class EmailConfiguration {
    private final EmailSender emailSender;

    @Bean
    EmailFacade emailFacade() {
        return new EmailFacade(new SendEmailUseCase(emailSender));
    }
}
