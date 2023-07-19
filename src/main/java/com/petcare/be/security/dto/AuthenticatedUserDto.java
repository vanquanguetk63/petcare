package com.petcare.be.security.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthenticatedUserDto {

	private String name;

	private String username;

	private String password;
}