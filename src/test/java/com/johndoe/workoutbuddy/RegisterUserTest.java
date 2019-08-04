package com.johndoe.workoutbuddy;


import com.johndoe.workoutbuddy.domain.user.dto.UserError;
import com.johndoe.workoutbuddy.domain.user.dto.UserRegisterDto;
import com.johndoe.workoutbuddy.domain.user.usecase.RegisterUser;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.jsonwebtoken.lang.Assert.isTrue;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisterUserTest {

	@Autowired
	private RegisterUser registerUser;

	@Test
	public void shouldRegisterUser() {
		assertTrue(registerUser.register(UserRegisterDto.builder()
				.username("test1")
				.email("admin@gmail.com")
				.password("abc").build()).isRight());
	}

	@Test
	public void shouldReturnInvalidEmail() {
		assertThat(registerUser.register(UserRegisterDto.builder()
				.username("test2")
				.email("admingmail.com")
				.password("abc").build()).getLeft(), is(UserError.INVALID_EMAIL));
	}

	@Test
	public void shouldReturnUserAlreadyExists() {
		assertTrue(registerUser.register(UserRegisterDto.builder()
				.username("test3")
				.email("admin@gmail.com")
				.password("abc").build()).isRight());
		assertThat(registerUser.register(UserRegisterDto.builder()
				.username("test3")
				.email("admin@gmail.com")
				.password("abc").build()).getLeft(), is(UserError.USERNAME_ALREADY_EXISTS));
	}

}
