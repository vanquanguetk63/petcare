package com.petcare.be.validation;

import com.petcare.be.exceptions.AuthenticationException;
import com.petcare.be.repository.UserRepository;
import com.petcare.be.security.dto.RegisterRequest;
import com.petcare.be.util.ExceptionMessageAccessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserValidationService {
    private static final String EMAIL_ALREADY_EXISTS = "email_already_exists";

    private static final String USERNAME_ALREADY_EXISTS = "username_already_exists";

    private final UserRepository userRepository;

    private final ExceptionMessageAccessor exceptionMessageAccessor;

    public void validateUser(RegisterRequest registerRequest) {
        final String userName = registerRequest.getUsername();
        final String email = registerRequest.getEmail();
        checkUserName(userName);
        checkEmail(email);
    }

    private void checkUserName(String userName) {
        final boolean existsByUsername = userRepository.existsByUsername(userName);

        if (existsByUsername) {

            log.warn("{} is already being used!", userName);

            throw new AuthenticationException(USERNAME_ALREADY_EXISTS);
        }
    }

    private void checkEmail(String email) {
        final boolean existByEmail = userRepository.existsByEmail(email);

        if (existByEmail) {

            log.warn("{} is already being email!", email);

//            final String existsUsername = exceptionMessageAccessor.getMessage(null, EMAIL_ALREADY_EXISTS);
            throw new AuthenticationException(EMAIL_ALREADY_EXISTS);
        }
    }
}
