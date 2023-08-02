package com.petcare.be.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.petcare.be.model.User;
import com.petcare.be.security.dto.UserInformationDto;
import com.petcare.be.security.dto.ValidateTokenRequestDto;
import com.petcare.be.security.dto.ValidateTokenResponseDto;
import com.petcare.be.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenManager {

    private final JwtProperties jwtProperties;
    public String generateToken(User user) {

        final String username = user.getUsername();
//		final UserRole userRole = user .getUserRole();

        return JWT.create()
                .withSubject(username)
                .withIssuer(jwtProperties.getIssuer())
//				.withClaim("role", userRole.name())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtProperties.getExpirationMinute() * 60 * 1000))
                .sign(Algorithm.HMAC256(jwtProperties.getSecretKey().getBytes()));
    }

    public String generateAccessToken(User user) {
        final String username = user.getUsername();

        return JWT.create()
                .withSubject(username)
                .withIssuer(jwtProperties.getIssuer())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtProperties.getExpirationAccessTokenMinute() * 60 * 1000))
                .sign(Algorithm.HMAC256(jwtProperties.getSecretKey().getBytes()));
    }

    public ValidateTokenResponseDto refreshToken(String accessToken, User user) {
        final boolean validateAccessToken = isTokenExpired(accessToken);
        if (validateAccessToken) {
            final String token = generateToken(user);
            final String newAccesToken = generateAccessToken(user);
            return new ValidateTokenResponseDto(token,newAccesToken,new UserInformationDto(user.getUsername(), user.getEmail()));
        } else {
            throw new Error("Expired Token");
        }
    }

    public String getUsernameFromToken(String token) {

        final DecodedJWT decodedJWT = getDecodedJWT(token);

        return decodedJWT.getSubject();
    }

    public boolean validateToken(String token, String authenticatedUsername) {

        final String usernameFromToken = getUsernameFromToken(token);

        final boolean equalsUsername = usernameFromToken.equals(authenticatedUsername);
        final boolean tokenExpired = isTokenExpired(token);

        return equalsUsername && !tokenExpired;
    }
    private boolean isTokenExpired(String token) {

        final Date expirationDateFromToken = getExpirationDateFromToken(token);
        final Date currentDate = new Date();
        return currentDate.before(expirationDateFromToken);
    }

    private Date getExpirationDateFromToken(String token) {

        final DecodedJWT decodedJWT = getDecodedJWT(token);

        return decodedJWT.getExpiresAt();
    }

    private String getSubject(String token) {

        final DecodedJWT decodedJWT = getDecodedJWT(token);

        return decodedJWT.getSubject();
    }

    private DecodedJWT getDecodedJWT(String token) {

        final JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(jwtProperties.getSecretKey().getBytes())).build();

        return jwtVerifier.verify(token);
    }

}
