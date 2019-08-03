package com.johndoe.workoutbuddy.adapter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class ResponseResolver {

    <T> ResponseEntity resolve(Optional<T> object) {
        return object
                .map(val -> new ResponseEntity<>(object, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
