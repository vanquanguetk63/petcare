package com.petcare.be.controller;


import com.petcare.be.security.dto.*;
import com.petcare.be.security.jwt.JwtTokenService;
import com.petcare.be.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final JwtTokenService jwtTokenService;
    private final UserService userService;
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginIn(@Valid @RequestBody LoginRequest loginRequest) {
        final LoginResponse loginResponse = jwtTokenService.getLoginResponse(loginRequest);

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        final RegisterResponse registrationResponse = userService.registration(registerRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(registrationResponse);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ValidateTokenResponseDto> validateToken(@Valid @RequestBody ValidateTokenRequestDto validateTokenRequestDto) {
        log.debug("validateTokenRequestDto", validateTokenRequestDto.getToken());
        final ValidateTokenResponseDto validateTokenResponseDto = jwtTokenService.getValidateToken(validateTokenRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(validateTokenResponseDto);
    }
}
