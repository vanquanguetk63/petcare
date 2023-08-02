package com.petcare.be.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class ValidateTokenResponseDto {

	private String token;

	private String accessToken;
	private UserInformationDto user;


}
