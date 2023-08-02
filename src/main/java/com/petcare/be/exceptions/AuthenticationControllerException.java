package com.petcare.be.exceptions;

import com.petcare.be.controller.AuthenticationController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice(basePackageClasses = AuthenticationController.class)
public class AuthenticationControllerException {

    @ExceptionHandler (BadCredentialsException.class)
    ResponseEntity<ApiExceptionResponse> handleRegistrationException(BadCredentialsException exception) {

        final ApiExceptionResponse response = new ApiExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());

        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
