package com.johndoe.workoutbuddy.domain.email;

import com.johndoe.workoutbuddy.domain.email.dto.UserActivationEmail;
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
public class SendEmailTest {

    @Mock
    private EmailFacade emailFacade;

    private UserActivationEmail validMessage() {
        return UserActivationEmail.builder().token(UUID.randomUUID()).receiver("aa@gmail.com").username("abc").build();
    }
    @Test
    public void shouldSendEmail() {
        assertTrue(emailFacade.sendUserVerificationEmail(validMessage()).isRight());
    }
}
