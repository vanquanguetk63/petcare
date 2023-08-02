package com.petcare.be.security.jwt;

import com.petcare.be.model.User;
import com.petcare.be.security.dto.*;
import com.petcare.be.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenService {

    private final AuthenticationService authenticationService;

    private final JwtTokenManager jwtTokenManager;

    private final AuthenticationManager authenticationManager;

    public LoginResponse getLoginResponse(LoginRequest loginRequest) {

        final String username = loginRequest.getUsername();
        final String password = loginRequest.getPassword();

        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        final User user = authenticationService.getInformationUser(username);
        final String token = jwtTokenManager.generateToken(user);
        final String accessToken = jwtTokenManager.generateAccessToken(user);
        final UserInformationDto userInformationDto = new UserInformationDto(user.getUsername(), user.getEmail());

        return new LoginResponse(token, accessToken, userInformationDto);
    }

    public ValidateTokenResponseDto getValidateToken(ValidateTokenRequestDto validateTokenDto) {
        final String username = validateTokenDto.getUsername();
        final User user = authenticationService.getInformationUser(username);
        return jwtTokenManager.refreshToken(validateTokenDto.getAccessToken(), user);
    }

}
