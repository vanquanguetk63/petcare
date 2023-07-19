package com.petcare.be.service;

import com.petcare.be.model.User;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
public interface UserService {

	User findByUsername(String username);

//	RegistrationResponse registration(RegistrationRequest registrationRequest);
//
//	AuthenticatedUserDto findAuthenticatedUserByUsername(String username);

}
