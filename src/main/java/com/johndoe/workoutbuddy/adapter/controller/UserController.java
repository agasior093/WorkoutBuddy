package com.johndoe.workoutbuddy.adapter.controller;

import com.johndoe.workoutbuddy.domain.user.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity registerUser() {
        return null;
    }

    @GetMapping("/confirmRegistration")
    ResponseEntity confirmRegistration(@RequestParam String confirmationToken) {
        return null;
    }


}
