package com.johndoe.workoutbuddy.adapter.controller;

import com.johndoe.workoutbuddy.domain.user.UserFacade;
import com.johndoe.workoutbuddy.domain.user.dto.RegisterUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
class UserController {

    private final ResponseResolver responseResolver;
    private final UserFacade userFacade;

    @GetMapping
    ResponseEntity getPersonalUserDetails(@RequestParam String username) {
        return responseResolver.resolve(userFacade.readUserPersonalDetails(username));
    }
    @PostMapping("/register")
    ResponseEntity registerUser(@RequestBody RegisterUserDto registerUserDto) {
        return responseResolver.resolve(userFacade.registerNewUser(registerUserDto));
    }

    @GetMapping("/confirm")
    ResponseEntity confirmRegistration(@RequestParam String token, @RequestParam String username) {
        return responseResolver.resolve(userFacade.confirmRegistration(token, username));
    }


}
