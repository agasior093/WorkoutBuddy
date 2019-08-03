package com.johndoe.workoutbuddy.adapter.controller;

import com.johndoe.workoutbuddy.domain.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final ResponseResolver responseResolver;
    private final UserReader userReader;

    @GetMapping
    ResponseEntity getPersonalUserDetails(@RequestParam String username) {
        return responseResolver.resolve(userReader.readPersonalData(username));
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
