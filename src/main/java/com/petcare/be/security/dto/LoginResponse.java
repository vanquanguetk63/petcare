package com.petcare.be.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
	private String token;
	private String accessToken;
	private UserInformationDto user;
}
