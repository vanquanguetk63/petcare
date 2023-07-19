package com.petcare.be.service;

import com.petcare.be.model.User;
import com.petcare.be.security.dto.RegisterRequest;
import com.petcare.be.security.dto.RegisterResponse;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
public interface UserService {
	RegisterResponse registration(RegisterRequest registrationRequest);

//	RegistrationResponse registration(RegistrationRequest registrationRequest);
//
//	AuthenticatedUserDto findAuthenticatedUserByUsername(String username);

}
