package com.johndoe.workoutbuddy.domain.email;

import com.johndoe.workoutbuddy.domain.email.model.UserActivationEmail;
import com.johndoe.workoutbuddy.domain.email.dto.error.EmailError;
import com.johndoe.workoutbuddy.domain.email.port.EmailSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class SendEmailTest {

    private EmailSender emailSender = Mockito.mock(EmailSender.class);
    private EmailFacade emailFacade = new EmailFacade(emailSender);

    @Test
    public void shouldSendEmail() {
        assertTrue(emailFacade.sendActivationEmail(any(), any(), any()).isRight());
    }

    @Test
    public void shouldCatchExceptionAndReturnSendingFail() {
        doThrow(new RuntimeException("Email exception")).when(emailSender).sendEmail(any());
        assertThat(emailFacade.sendActivationEmail(any(), any(), any()).getLeft(), is(EmailError.SENDING_FAILED));
    }

    private UserActivationEmail validMessage() {
        return UserActivationEmail.builder().token("token").receiver("mail@gmail.com").username("username").build();
    }
}
