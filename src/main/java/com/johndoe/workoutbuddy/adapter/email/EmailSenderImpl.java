package com.johndoe.workoutbuddy.adapter.email;

import com.johndoe.workoutbuddy.domain.email.dto.EmailMessage;
import com.johndoe.workoutbuddy.domain.email.port.EmailSender;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailSenderImpl implements EmailSender {
    private JavaMailSender emailSender;
    private EmailContentBuilder contentBuilder;

    @Override
    public void sendEmail(EmailMessage emailMessage) throws RuntimeException {
        MimeMessage email = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(email, true);
            helper.setTo(emailMessage.getReceiver());
            helper.setSubject(emailMessage.getSubject());
            helper.setText(contentBuilder.buildVerificationEmail(emailMessage), true);
            emailSender.send(email);
        } catch (MessagingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
