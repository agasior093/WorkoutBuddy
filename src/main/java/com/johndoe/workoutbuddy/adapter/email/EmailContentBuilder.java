package com.johndoe.workoutbuddy.adapter.email;

import com.johndoe.workoutbuddy.domain.email.dto.UserActivationEmail;
import com.johndoe.workoutbuddy.domain.email.dto.EmailMessage;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
@RequiredArgsConstructor
class EmailContentBuilder {
    private final TemplateEngine templateEngine;

    @Value( "${server.port}" )
    private String port;

    @Value( "${server.hostname}" )
    private String host;

    String buildActivationEmail(EmailMessage emailMessage) {
        var activationMessage = (UserActivationEmail)emailMessage;
        var context = new Context();
        var url = "http://" + host + ":" + port + "/user/activate?token={token}&username={username}";
        context.setVariable("url", url.replace("{token}",
                activationMessage.getToken().toString()).replace("{username}", activationMessage.getUsername()));
        return templateEngine.process("activationTemplate", context);
    }

}
