package com.johndoe.workoutbuddy.adapter.controller;

import com.johndoe.workoutbuddy.domain.DomainError;
import com.johndoe.workoutbuddy.domain.email.dto.EmailError;
import com.johndoe.workoutbuddy.domain.user.dto.UserError;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
class ResponseResolver {

    private final Map<DomainError, HttpStatus> httpStatusMap = new HashMap();

    ResponseResolver() {
        httpStatusMap.put(UserError.INVALID_EMAIL, HttpStatus.BAD_REQUEST);
        httpStatusMap.put(UserError.USERNAME_ALREADY_EXISTS, HttpStatus.CONFLICT);
        httpStatusMap.put(EmailError.EMPTY_MESSAGE, HttpStatus.BAD_REQUEST);
    }

    <T> ResponseEntity resolve(Optional<T> object) {
        return object
                .map(val -> new ResponseEntity<>(object, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    <T> ResponseEntity resolve(Either<DomainError, T> either) {
        return either
                .map(this::successResponse)
                .getOrElseGet(this::failureResponse);
    }

    private ResponseEntity<Object> successResponse(Object obj) {
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    private ResponseEntity<Object> failureResponse(DomainError error) {
        return new ResponseEntity<>(error, httpStatusMap.get(error));
    }
}
