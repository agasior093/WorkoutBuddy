package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.configuration.domain.UserConfiguration;
import com.johndoe.workoutbuddy.infrastructure.database.user.InMemoryActivationTokenRepository;
import com.johndoe.workoutbuddy.infrastructure.database.user.InMemoryUserRepository;
import com.johndoe.workoutbuddy.common.messages.Success;
import com.johndoe.workoutbuddy.domain.email.EmailFacade;
import com.johndoe.workoutbuddy.domain.email.dto.error.EmailError;
import com.johndoe.workoutbuddy.domain.user.dto.CreateUserDto;
import com.johndoe.workoutbuddy.domain.user.dto.error.UserError;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import io.vavr.control.Either;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.Matchers.is;
import static com.johndoe.workoutbuddy.domain.user.ObjectFactory.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateUserTest {

    private EmailFacade emailFacade = mock(EmailFacade.class);
    private UserRepository userRepository = new InMemoryUserRepository();
    private UserFacade userFacade = new UserConfiguration().userFacade(userRepository, new InMemoryActivationTokenRepository(), emailFacade);

    @Before
    public void resetDatabase() {
        userRepository = new InMemoryUserRepository();
    }

    @Test
    public void shouldCreateInactiveUser() {
        //given
        var user = (CreateUserDto.builder()
                .username(USERNAME_1)
                .email(VALID_EMAIL_1)
                .password(VALID_PASSWORD).build());
        //when
        when(emailFacade.sendActivationEmail(anyString(), anyString(), anyString())).thenReturn(Either.right(new Success("")));
        var result = userFacade.createUser(user);
        var foundUser = userFacade.readUser(USERNAME_1);
        //then
        assertTrue(result.isRight());
        assertTrue(foundUser.isPresent());
        assertFalse(foundUser.get().isActive());
    }

    @Test
    public void shouldReturnInvalidEmail() {
        //given
        var userWithInvalidEmail = CreateUserDto.builder()
                .username(USERNAME_1)
                .email(INVALID_EMAIL)
                .password(VALID_PASSWORD).build();
        //when
        var result = userFacade.createUser(userWithInvalidEmail);
        //then
        assertThat(result.getLeft(), is(UserError.INVALID_EMAIL));
    }

    @Test
    public void shouldReturnUserAlreadyExists() {
        //given
        var user1 = CreateUserDto.builder()
                .username(USERNAME_1)
                .email(VALID_EMAIL_1)
                .password(VALID_PASSWORD).build();
        var user2 = CreateUserDto.builder()
                .username(USERNAME_1)
                .email(VALID_EMAIL_2)
                .password(VALID_PASSWORD).build();
        //when
        when(emailFacade.sendActivationEmail(anyString(), anyString(), anyString())).thenReturn(Either.right(new Success("")));
        var result1 = userFacade.createUser(user1);
        var result2 = userFacade.createUser(user2);
        //then
        assertTrue(result1.isRight());
        assertThat(result2.getLeft(), is(UserError.USERNAME_ALREADY_EXISTS));
    }

    @Test
    public void shouldReturnEmailAlreadyExists() {
        //given
        var user1 = CreateUserDto.builder()
                .username(USERNAME_1)
                .email(VALID_EMAIL_1)
                .password(VALID_PASSWORD).build();
        var user2 = CreateUserDto.builder()
                .username(USERNAME_2)
                .email(VALID_EMAIL_1)
                .password(VALID_PASSWORD).build();
        //when
        when(emailFacade.sendActivationEmail(anyString(), anyString(), anyString())).thenReturn(Either.right(new Success("")));
        var result1 = userFacade.createUser(user1);
        var result2 = userFacade.createUser(user2);
        //then
        assertTrue(result1.isRight());
        assertThat(result2.getLeft(), is(UserError.EMAIL_ALREADY_EXISTS));
    }

    @Test
    public void shouldReturnSendingFailed() {
        //given
        var user = CreateUserDto.builder()
                .username(USERNAME_1)
                .email(VALID_EMAIL_1)
                .password(VALID_PASSWORD).build();
        //when
        when(emailFacade.sendActivationEmail(anyString(), anyString(), anyString())).thenReturn(Either.left(EmailError.SENDING_FAILED));
        var result = userFacade.createUser(user);
        //then
        assertThat(result.getLeft(), is(EmailError.SENDING_FAILED));
    }

}
