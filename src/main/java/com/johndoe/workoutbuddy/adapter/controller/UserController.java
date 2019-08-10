package com.johndoe.workoutbuddy.adapter.controller;

import com.johndoe.workoutbuddy.domain.user.UserFacade;
import com.johndoe.workoutbuddy.domain.user.dto.RegisterUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
class UserController {

    private final ResponseResolver responseResolver;
    private final UserFacade userFacade;

    @GetMapping()
    ResponseEntity getUser(@RequestParam String username) {
        return responseResolver.resolve(userFacade.readUser(username));
    }

    @GetMapping("/personal")
    ResponseEntity getPersonalUserDetails(@RequestParam String username) {
        return responseResolver.resolve(userFacade.readUserPersonalData(username));
    }
    
    @PostMapping("/register")
    ResponseEntity registerUser(@RequestBody RegisterUserDto registerUserDto) {
        return responseResolver.resolve(userFacade.createUser(registerUserDto));
    }

    @GetMapping("/activate")
    ResponseEntity confirmRegistration(@RequestParam String token, @RequestParam String username) {
        return responseResolver.resolve(userFacade.activateUser(token, username));
    }


}
