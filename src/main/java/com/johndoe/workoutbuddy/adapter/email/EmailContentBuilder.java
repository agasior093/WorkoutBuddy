package com.johndoe.workoutbuddy.adapter.email;

import com.johndoe.workoutbuddy.domain.email.dto.VerificationEmail;
import com.johndoe.workoutbuddy.domain.email.dto.EmailMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
@AllArgsConstructor
class EmailContentBuilder {
    private TemplateEngine templateEngine;

    String buildVerificationEmail(EmailMessage emailMessage) {
        var verificationMessage = (VerificationEmail)emailMessage;
        var context = new Context();
        var url = "http://localhost:8080/user/confirm?token={token}&username={username}";
        context.setVariable("url", url.replace("{token}",
                verificationMessage.getToken().toString()).replace("{username}", verificationMessage.getUsername()));
        return templateEngine.process("confirmationTemplate", context);
    }

}
