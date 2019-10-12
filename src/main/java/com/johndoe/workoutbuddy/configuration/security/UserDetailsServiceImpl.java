package com.johndoe.workoutbuddy.configuration.security;

import com.johndoe.workoutbuddy.domain.user.model.User;
import com.johndoe.workoutbuddy.domain.user.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).map(this::getUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("ApplicationUser not found"));
    }

    private UserDetails getUserDetails(User user) {
        org.springframework.security.core.userdetails.User.UserBuilder builder
                = org.springframework.security.core.userdetails.User.withUsername(user.getUsername());
        builder.password(user.getPassword());
        builder.roles(user.getRoles().toArray(new String[user.getRoles().size()]));
        builder.disabled(!user.isActive());
        builder.build();
        return builder.build();
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
         this.userRepository = userRepository;
    }
}
