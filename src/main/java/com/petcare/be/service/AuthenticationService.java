package com.petcare.be.service;

import com.petcare.be.mapper.UserMapper;
import com.petcare.be.model.User;
import com.petcare.be.repository.UserRepository;
import com.petcare.be.security.dto.AuthenticatedUserDto;
import com.petcare.be.security.dto.RegisterRequest;
import com.petcare.be.security.dto.RegisterResponse;
import com.petcare.be.util.GeneralMessageAccessor;
import com.petcare.be.validation.UserValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserService {
    private static final String REGISTRATION_SUCCESSFUL = "registration_successful";
    private final UserRepository userRepository;
    private final UserValidationService userValidationService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final GeneralMessageAccessor generalMessageAccessor;

    @Override
    public RegisterResponse registration(RegisterRequest registrationRequest) {
        userValidationService.validateUser(registrationRequest);

        final User user = UserMapper.INSTANCE.convertToUser(registrationRequest);
        user.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
        userRepository.save(user);

//        final String username = registrationRequest.getUsername();
//        final String registrationSuccessMessage = generalMessageAccessor.getMessage(null, REGISTRATION_SUCCESSFUL, username);

        return new RegisterResponse(REGISTRATION_SUCCESSFUL, registrationRequest.getUsername());
    }

    private User findByUsername(String username) {
        try {
            return userRepository.findByUsername(username);
        } catch (Exception exception) {
            throw new Error("Not found user");
        }
    }

    public AuthenticatedUserDto findAuthenticatedUserByUsername(String username) {

        final User user = this.findByUsername(username);
        return UserMapper.INSTANCE.convertToAuthenticatedUserDto(user);
    }

    public User getInformationUser(String username) {
        return this.findByUsername(username);
    }
}
