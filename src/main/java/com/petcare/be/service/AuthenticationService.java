package com.petcare.be.service;

import com.petcare.be.mapper.UserMapper;
import com.petcare.be.model.User;
import com.petcare.be.repository.UserRepository;
import com.petcare.be.security.dto.AuthenticatedUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;

    private User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public AuthenticatedUserDto findAuthenticatedUserByUsername(String username) {

        final User user = this.findByUsername(username);

        return UserMapper.INSTANCE.convertToAuthenticatedUserDto(user);
    }
}
