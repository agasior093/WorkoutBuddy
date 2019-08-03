package com.johndoe.workoutbuddy.adapter.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/admin")
    public ResponseEntity<?> adminData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return new ResponseEntity(new Response("This is admin data, hello " + currentPrincipalName), HttpStatus.OK);
    }

    @GetMapping("/private")
    public ResponseEntity<?> privateData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return new ResponseEntity(new Response("This is private data, hello " + currentPrincipalName), HttpStatus.OK);
    }

    @GetMapping("/public")
    public ResponseEntity publicData() {
        return new ResponseEntity(new Response("This is public data"), HttpStatus.OK);
    }

    class Response {
        String content;
        public Response(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }
    }
}
