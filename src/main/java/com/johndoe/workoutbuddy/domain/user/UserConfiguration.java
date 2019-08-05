package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.domain.email.EmailFacade;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class UserConfiguration {
    private final UserRepository userRepository;
    private final EmailFacade emailFacade;

    @Bean
    UserFacade userFacade() {
        ObjectMapper mapper = new ObjectMapper();
        ReadUserUseCase readUser = new ReadUserUseCase(userRepository, mapper);
        RegisterUserUseCase registerUser = new RegisterUserUseCase(userRepository, emailFacade, mapper);
        ConfirmRegistrationUseCase confirmRegistration = new ConfirmRegistrationUseCase(userRepository, mapper);
        return new UserFacade(readUser, registerUser, confirmRegistration, mapper);
    }
}
