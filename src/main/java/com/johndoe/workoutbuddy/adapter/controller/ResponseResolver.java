package com.johndoe.workoutbuddy.adapter.controller;

import com.johndoe.workoutbuddy.domain.DomainError;
import com.johndoe.workoutbuddy.domain.email.dto.error.EmailError;
import com.johndoe.workoutbuddy.domain.user.dto.UserError;
import io.vavr.control.Either;
import lombok.extern.java.Log;
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
        httpStatusMap.put(UserError.EMAIL_ALREADY_EXISTS, HttpStatus.CONFLICT);
        httpStatusMap.put(UserError.INVALID_TOKEN, HttpStatus.CONFLICT);
        httpStatusMap.put(UserError.EXPIRED_REGISTRATION_TOKEN, HttpStatus.CONFLICT);
        httpStatusMap.put(UserError.ACTIVATION_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
        httpStatusMap.put(EmailError.SENDING_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
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
        return new ResponseEntity<>(error, getHttpStatus(error));
    }

    private HttpStatus getHttpStatus(DomainError error) {
        return error != null && httpStatusMap.get(error) != null ? httpStatusMap.get(error) : HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
