package com.johndoe.workoutbuddy.adapter.controller;

import com.johndoe.workoutbuddy.domain.common.Error;
import com.johndoe.workoutbuddy.domain.email.dto.error.EmailError;
import com.johndoe.workoutbuddy.domain.user.dto.UserError;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
class ResponseResolver {

    private final Map<Error, HttpStatus> httpStatusMap = new HashMap();

    ResponseResolver() {
        httpStatusMap.put(UserError.INVALID_EMAIL, HttpStatus.BAD_REQUEST);
        httpStatusMap.put(UserError.USERNAME_ALREADY_EXISTS, HttpStatus.CONFLICT);
        httpStatusMap.put(UserError.EMAIL_ALREADY_EXISTS, HttpStatus.CONFLICT);
        httpStatusMap.put(UserError.ACTIVATION_TOKEN_NOT_FOUND, HttpStatus.NOT_FOUND);
        httpStatusMap.put(UserError.EXPIRED_ACTIVATION_TOKEN, HttpStatus.CONFLICT);
        httpStatusMap.put(UserError.ACTIVATION_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
        httpStatusMap.put(EmailError.SENDING_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    <T> ResponseEntity resolve(Optional<T> object) {
        return object
                .map(val -> new ResponseEntity<>(object, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    <T> ResponseEntity resolve(Either<Error, T> either) {
        return either
                .map(this::successResponse)
                .getOrElseGet(this::failureResponse);
    }

    <T> ResponseEntity resolve(List<T> objectList) {
        return objectList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(objectList, HttpStatus.OK);
    }

    private ResponseEntity<Object> successResponse(Object obj) {
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    private ResponseEntity<Object> failureResponse(Error error) {
        return new ResponseEntity<>(error, getHttpStatus(error));
    }

    private HttpStatus getHttpStatus(Error error) {
        return error != null && httpStatusMap.get(error) != null ? httpStatusMap.get(error) : HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
