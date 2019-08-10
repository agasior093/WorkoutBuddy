package com.johndoe.workoutbuddy.domain.user;
import com.johndoe.workoutbuddy.adapter.repository.inmemory.InMemoryActivationTokenRepository;
import com.johndoe.workoutbuddy.adapter.repository.inmemory.InMemoryUserRepository;
import com.johndoe.workoutbuddy.domain.SuccessMessage;
import com.johndoe.workoutbuddy.domain.email.EmailFacade;
import com.johndoe.workoutbuddy.domain.email.dto.error.EmailError;
import com.johndoe.workoutbuddy.domain.user.dto.RegisterUserDto;
import com.johndoe.workoutbuddy.domain.user.dto.UserError;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import io.vavr.control.Either;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static com.johndoe.workoutbuddy.domain.user.ObjectFactory.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateUserTest {

	private EmailFacade emailFacade = Mockito.mock(EmailFacade.class);
	private UserRepository userRepository = new InMemoryUserRepository();
	private UserFacade userFacade = new UserConfiguration().userFacade(userRepository, new InMemoryActivationTokenRepository(), emailFacade);

	@Before
	public void resetDatabase() {
		userRepository = new InMemoryUserRepository();
	}

	@Test
	public void shouldCreateInactiveUser() {
		Mockito.when(emailFacade.sendActivationEmail(Mockito.any())).thenReturn(Either.right(new SuccessMessage("")));
		assertTrue(userFacade.createUser(RegisterUserDto.builder()
				.username(USERNAME_1)
				.email(VALID_EMAIL_1)
				.password(VALID_PASSWORD).build()).isRight());
		var user = userFacade.readUser(USERNAME_1);
		assertTrue(user.isPresent());
		assertFalse(user.get().isActive());
	}

	@Test
	public void shouldReturnInvalidEmail() {
		assertThat(userFacade.createUser(RegisterUserDto.builder()
				.username(USERNAME_1)
				.email(INVALID_EMAIL)
				.password(VALID_PASSWORD).build()).getLeft(), is(UserError.INVALID_EMAIL));
	}

	@Test
	public void shouldReturnUserAlreadyExists() {
		Mockito.when(emailFacade.sendActivationEmail(Mockito.any())).thenReturn(Either.right(new SuccessMessage("")));
		assertTrue(userFacade.createUser(RegisterUserDto.builder()
				.username(USERNAME_1)
				.email(VALID_EMAIL_1)
				.password(VALID_PASSWORD).build()).isRight());
		assertThat(userFacade.createUser(RegisterUserDto.builder()
				.username(USERNAME_1)
				.email(VALID_EMAIL_2)
				.password(VALID_PASSWORD).build()).getLeft(), is(UserError.USERNAME_ALREADY_EXISTS));
	}

	@Test
	public void shouldReturnEmailAlreadyExists() {
		Mockito.when(emailFacade.sendActivationEmail(Mockito.any())).thenReturn(Either.right(new SuccessMessage("")));
		assertTrue(userFacade.createUser(RegisterUserDto.builder()
				.username(USERNAME_1)
				.email(VALID_EMAIL_1)
				.password(VALID_PASSWORD).build()).isRight());
		assertThat(userFacade.createUser(RegisterUserDto.builder()
				.username(USERNAME_2)
				.email(VALID_EMAIL_1)
				.password(VALID_PASSWORD).build()).getLeft(), is(UserError.EMAIL_ALREADY_EXISTS));
	}

	@Test
	public void shouldReturnSendingFailed() {
		Mockito.when(emailFacade.sendActivationEmail(Mockito.any())).thenReturn(Either.left(EmailError.SENDING_FAILED));
		assertThat(userFacade.createUser(RegisterUserDto.builder()
				.username(USERNAME_1)
				.email(VALID_EMAIL_1)
				.password(VALID_PASSWORD).build()).getLeft(), is(EmailError.SENDING_FAILED));
	}

}
