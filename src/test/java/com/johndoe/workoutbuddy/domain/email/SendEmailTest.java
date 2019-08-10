package com.johndoe.workoutbuddy.domain.email;

import com.johndoe.workoutbuddy.domain.email.dto.UserActivationEmail;
import com.johndoe.workoutbuddy.domain.email.dto.error.EmailError;
import com.johndoe.workoutbuddy.domain.email.port.EmailSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class SendEmailTest {

    private EmailSender emailSender = Mockito.mock(EmailSender.class);
    private EmailFacade emailFacade = new EmailFacade(emailSender);

    @Test
    public void shouldSendEmail() {
        assertTrue(emailFacade.sendActivationEmail("", "", "").isRight());
    }

    @Test
    public void shouldCatchExceptionAndReturnSendingFail() {
        var email = validMessage();
        doThrow(new RuntimeException("Email exception")).when(emailSender).sendEmail(email);
        assertThat(emailFacade.sendActivationEmail(validMessage().getUsername(), validMessage().getReceiver(), validMessage().getToken()).getLeft(), is(EmailError.SENDING_FAILED));
    }

    private UserActivationEmail validMessage() {
        return UserActivationEmail.builder().token("token").receiver("mail@gmail.com").username("username").build();
    }
}
