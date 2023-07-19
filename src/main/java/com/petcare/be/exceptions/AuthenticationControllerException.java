package com.petcare.be.exceptions;

import com.petcare.be.controller.AuthenticationController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice(basePackageClasses = AuthenticationController.class)
public class AuthenticationControllerException {

    @ExceptionHandler (AuthenticationException.class)
    ResponseEntity<ApiExceptionResponse> handleRegistrationException(AuthenticationException exception) {

        final ApiExceptionResponse response = new ApiExceptionResponse(exception.getErrorMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());

        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
