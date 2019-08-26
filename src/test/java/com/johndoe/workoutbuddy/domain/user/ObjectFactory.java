package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.domain.user.dto.CreateUserDto;
import com.johndoe.workoutbuddy.domain.user.model.User;

class ObjectFactory {
    static final String USERNAME_1 = "test1";
    static final String USERNAME_2 = "test2";
    static final String VALID_PASSWORD = "abcd";
    static final String VALID_EMAIL_1 = "email1@gmail.com";
    static final String VALID_EMAIL_2 = "email2@gmail.com";
    static final String INVALID_EMAIL = "mailgmail.com";

    static CreateUserDto validRegisterDto() {
        return CreateUserDto.builder().username(USERNAME_1).email(VALID_EMAIL_1).password(VALID_PASSWORD).build();
    }

    static User validUser() {
        return User.builder().username(USERNAME_1).email(VALID_EMAIL_1).password(VALID_PASSWORD).build();
    }
}
