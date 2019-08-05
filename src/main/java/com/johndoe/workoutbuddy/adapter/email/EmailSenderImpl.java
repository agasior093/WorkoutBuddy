package com.johndoe.workoutbuddy.adapter.email;

import com.johndoe.workoutbuddy.domain.email.port.EmailSender;
import com.johndoe.workoutbuddy.domain.email.dto.EmailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderImpl implements EmailSender {

    private JavaMailSender emailSender;

    @Override
    public void sendEmail(EmailMessage emailMessage) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailMessage.getReceiver());
        message.setSubject(emailMessage.getSubject());
        message.setText(emailMessage.getBody());
        emailSender.send(message);
    }

    @Autowired
    public void setEmailSender(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }
}
