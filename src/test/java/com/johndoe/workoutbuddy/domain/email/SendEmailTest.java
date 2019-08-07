package com.johndoe.workoutbuddy.domain.email;

import com.johndoe.workoutbuddy.domain.SuccessMessage;
import com.johndoe.workoutbuddy.domain.email.dto.EmailMessage;
import com.johndoe.workoutbuddy.domain.email.dto.VerificationEmail;
import com.johndoe.workoutbuddy.domain.email.port.EmailSender;
import io.vavr.control.Either;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class SendEmailTest {

    @Mock
    private EmailFacade emailFacade;

    private VerificationEmail validMessage() {
        return VerificationEmail.builder().token(UUID.randomUUID()).receiver("aa@gmail.com").username("abc").build();
    }
    @Test
    public void shouldSendEmail() {
        assertTrue(emailFacade.sendUserVerificationEmail(validMessage()).isRight());
    }
}
