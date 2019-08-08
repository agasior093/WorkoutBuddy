package com.johndoe.workoutbuddy.configuration.security;

import com.johndoe.workoutbuddy.adapter.repository.InMemoryUserRepository;
import com.johndoe.workoutbuddy.domain.user.dto.UserDto;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
class UserDetailsServiceImpl implements UserDetailsService {
    private final PasswordEncoder encoder;
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUser(username).map(this::getUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("ApplicationUser not found"));
    }

    private UserDetails getUserDetails(UserDto user) {
        org.springframework.security.core.userdetails.User.UserBuilder builder
                = org.springframework.security.core.userdetails.User.withUsername(user.getUsername());
        builder.password(encoder.encode(user.getPassword()));
        builder.roles(user.getRoles().toArray(new String[user.getRoles().size()]));
        builder.disabled(!user.isActive());
        builder.build();
        return builder.build();
    }

    @Autowired
    public void setUserRepository(InMemoryUserRepository userRepository) {
         this.userRepository = userRepository;
    }
}
