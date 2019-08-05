package com.johndoe.workoutbuddy.domain.user;


//import com.johndoe.workoutbuddy.domain.user.RegisterUser;
import com.johndoe.workoutbuddy.adapter.repository.InMemoryUserRepository;
import com.johndoe.workoutbuddy.domain.email.EmailFacade;

import com.johndoe.workoutbuddy.domain.user.dto.RegisterUserDto;
import com.johndoe.workoutbuddy.domain.user.dto.UserError;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static io.jsonwebtoken.lang.Assert.isTrue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class RegisterUserTest {

	private static final String USERNAME_1 = "test1";
	private static final String USERNAME_2 = "test2";
	private static final String USERNAME_3 = "test3";
	private static final String USERNAME_4 = "test4";
	private static final String VALID_PASSWORD = "abcd";
	private static final String VALID_EMAIL = "email@gmail.com";
	private static final String INVALID_EMAIL = "mailgmail.com";

	@Mock
	private EmailFacade emailFacade;
	private UserRepository repository = new InMemoryUserRepository();
	private ObjectMapper mapper = new ObjectMapper();

	private RegisterUserUseCase registerUserUseCase;
	private UserFacade userFacade;


	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		registerUserUseCase = new RegisterUserUseCase(repository, emailFacade, mapper);
		userFacade = new UserFacade(null, registerUserUseCase, null, null);
	}

	@Test
	public void shouldRegisterUser() {
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
		assertTrue(userFacade.registerNewUser(RegisterUserDto.builder()
				.username(USERNAME_3)
				.email(VALID_EMAIL)
				.password(VALID_PASSWORD).build()).isRight());
		assertThat(userFacade.registerNewUser(RegisterUserDto.builder()
				.username(USERNAME_3)
				.email(VALID_EMAIL)
				.password(VALID_PASSWORD).build()).getLeft(), is(UserError.USERNAME_ALREADY_EXISTS));
	}

//	@Test
//	public void shouldReturnEmptyMessage() {
//		VerificationEmail dto = new VerificationEmail(UUID.randomUUID(), "");
//		when(emailFacade.sendUserVerificationEmail(dto)).thenReturn(Either.left(EmailError.EMPTY_MESSAGE));
//		assertThat(userFacade.registerNewUser(RegisterUserDto.builder()
//				.username(USERNAME_4)
//				.email(VALID_EMAIL)
//				.password(VALID_PASSWORD).build()).getLeft(), is(EmailError.EMPTY_MESSAGE));
//	}

}
