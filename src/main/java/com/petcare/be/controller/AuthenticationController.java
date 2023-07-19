package com.petcare.be.controller;


import com.petcare.be.security.dto.LoginRequest;
import com.petcare.be.security.dto.LoginResponse;
import com.petcare.be.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final JwtTokenService jwtTokenService;
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginIn(@Valid @RequestBody LoginRequest loginRequest) {
        final LoginResponse loginResponse = jwtTokenService.getLoginResponse(loginRequest);

        return ResponseEntity.ok(loginResponse);
    }
}
