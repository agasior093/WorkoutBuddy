package com.johndoe.workoutbuddy.domain.user;


//import com.johndoe.workoutbuddy.domain.user.RegisterUser;

import com.johndoe.workoutbuddy.adapter.repository.InMemoryUserRepository;
import com.johndoe.workoutbuddy.domain.SuccessMessage;
import com.johndoe.workoutbuddy.domain.email.EmailFacade;
import com.johndoe.workoutbuddy.domain.email.dto.error.EmailError;
import com.johndoe.workoutbuddy.domain.user.dto.RegisterUserDto;
import com.johndoe.workoutbuddy.domain.user.dto.UserError;
import io.vavr.control.Either;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import static com.johndoe.workoutbuddy.domain.user.ObjectFactory.*;

@RunWith(MockitoJUnitRunner.class)
public class RegisterUserTest {

	private EmailFacade emailFacade = Mockito.mock(EmailFacade.class);
	private UserFacade userFacade = new UserConfiguration().userFacade(new InMemoryUserRepository(), emailFacade);

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldRegisterUser() {
		Mockito.when(emailFacade.sendUserVerificationEmail(Mockito.any())).thenReturn(Either.right(new SuccessMessage("")));
		assertTrue(userFacade.registerNewUser(RegisterUserDto.builder()
				.username(USERNAME_1)
				.email(VALID_EMAIL)
				.password(VALID_PASSWORD).build()).isRight());
	}

	@Test
	public void shouldReturnInvalidEmail() {
		assertThat(userFacade.registerNewUser(RegisterUserDto.builder()
				.username(USERNAME_2)
				.email(INVALID_EMAIL)
				.password(VALID_PASSWORD).build()).getLeft(), is(UserError.INVALID_EMAIL));
	}

	@Test
	public void shouldReturnUserAlreadyExists() {
		Mockito.when(emailFacade.sendUserVerificationEmail(Mockito.any())).thenReturn(Either.right(new SuccessMessage("")));
		assertTrue(userFacade.registerNewUser(RegisterUserDto.builder()
				.username(USERNAME_3)
				.email(VALID_EMAIL)
				.password(VALID_PASSWORD).build()).isRight());
		assertThat(userFacade.registerNewUser(RegisterUserDto.builder()
				.username(USERNAME_3)
				.email(VALID_EMAIL)
				.password(VALID_PASSWORD).build()).getLeft(), is(UserError.USERNAME_ALREADY_EXISTS));
	}

	@Test
	public void shouldReturnSendingFailed() {
		Mockito.when(emailFacade.sendUserVerificationEmail(Mockito.any())).thenReturn(Either.left(EmailError.SENDING_FAILED));
		assertThat(userFacade.registerNewUser(RegisterUserDto.builder()
				.username(USERNAME_4)
				.email(VALID_EMAIL)
				.password(VALID_PASSWORD).build()).getLeft(), is(EmailError.SENDING_FAILED));
	}

}
