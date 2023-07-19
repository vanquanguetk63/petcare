package com.petcare.be.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthenticationException extends RuntimeException {
    private final String errorMessage;
}
