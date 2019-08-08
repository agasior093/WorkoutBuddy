package com.johndoe.workoutbuddy.adapter.email;

import com.johndoe.workoutbuddy.domain.email.dto.UserActivationEmail;
import com.johndoe.workoutbuddy.domain.email.dto.EmailMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
@AllArgsConstructor
class EmailContentBuilder {
    private TemplateEngine templateEngine;

    String buildActivationEmail(EmailMessage emailMessage) {
        var activationMessage = (UserActivationEmail)emailMessage;
        var context = new Context();
        var url = "http://localhost:8080/user/activate?token={token}&username={username}";
        context.setVariable("url", url.replace("{token}",
                activationMessage.getToken().toString()).replace("{username}", activationMessage.getUsername()));
        return templateEngine.process("activationTemplate", context);
    }

}
