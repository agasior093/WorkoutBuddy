package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.configuration.domain.UserConfiguration;
import com.johndoe.workoutbuddy.infrastructure.database.InMemoryRepository;
import com.johndoe.workoutbuddy.infrastructure.database.user.InMemoryActivationTokenRepository;
import com.johndoe.workoutbuddy.infrastructure.database.user.InMemoryUserRepository;
import com.johndoe.workoutbuddy.infrastructure.database.user.ActivationTokenEntity;
import com.johndoe.workoutbuddy.domain.email.EmailFacade;
import com.johndoe.workoutbuddy.domain.user.port.ActivationTokenRepository;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Field;
import java.util.Map;

import static com.johndoe.workoutbuddy.domain.user.ObjectFactory.validRegisterDto;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@Log
public class ActivateUserTest {

    private EmailFacade emailFacade = Mockito.mock(EmailFacade.class);
    private UserRepository userRepository = new InMemoryUserRepository();
    private ActivationTokenRepository tokenRepository = new InMemoryActivationTokenRepository();
    private UserFacade userFacade = new UserConfiguration().userFacade(userRepository, tokenRepository, emailFacade);

    @Test
    public void shouldActivateUser() {
        String username = validRegisterDto().getUsername();
        userFacade.createUser(validRegisterDto());
        var user = userFacade.readUser(username);
        var tokenID = extractToken();
        log.info(tokenRepository.findToken(tokenID).get().toString());
        assertTrue(user.isPresent() && !user.get().isActive());
        assertTrue(userFacade.activateUser(tokenID, username).isRight());
        user = userFacade.readUser(username);
        assertTrue(user.isPresent() && user.get().isActive());
        var token = tokenRepository.findToken(tokenID);
        log.info(tokenRepository.findToken(tokenID).get().toString());
        assertTrue(token.isPresent() && token.get().isActivated());
    }

    //Token is sent via email, and at that point the only way to get it is via reflection
    private String extractToken()  {
      try {
          Field field = InMemoryRepository.class.getDeclaredField("repository");
          field.setAccessible(true);
          Map<String, ActivationTokenEntity> tokens = (Map<String, ActivationTokenEntity>)field.get(tokenRepository);
          var iterator = tokens.values().iterator();
          return iterator.next().getId();
      } catch (Exception e) {
          return null;
      }
    }
}