package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.infrastructure.repository.inmemory.InMemoryActivationTokenRepository;
import com.johndoe.workoutbuddy.infrastructure.repository.inmemory.InMemoryUserRepository;
import com.johndoe.workoutbuddy.infrastructure.repository.entity.ActivationTokenEntity;
import com.johndoe.workoutbuddy.domain.email.EmailFacade;
import com.johndoe.workoutbuddy.domain.user.port.ActivationTokenRepository;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.UUID;

import static com.johndoe.workoutbuddy.domain.user.ObjectFactory.validRegisterDto;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
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
        assertTrue(user.isPresent() && !user.get().isActive());
        assertTrue(userFacade.activateUser(extractToken(), username).isRight());
        user = userFacade.readUser(username);
        assertTrue(user.isPresent() && user.get().isActive());
    }

    //Token is sent via email, and at that point the only way to get it is via reflection
    private String extractToken()  {
      try {
          Field field = InMemoryActivationTokenRepository.class.getDeclaredField("tokens");
          field.setAccessible(true);
          Map<UUID, ActivationTokenEntity> tokens = (Map<UUID, ActivationTokenEntity>)field.get(tokenRepository);
          var iterator = tokens.values().iterator();
          return iterator.next().getTokenID();
      } catch (Exception e) {
          return null;
      }
    }
}