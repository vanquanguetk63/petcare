package com.petcare.be.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class ValidateTokenRequestDto {
	@NotEmpty(message = "Can not empty user name")
	private String username;

	@NotEmpty(message = "Can not empty token")
	private String token;

	@NotEmpty(message = "Can not empty access token")
	private String accessToken;

}
