package com.petcare.be.mapper;

import com.petcare.be.model.User;
import com.petcare.be.security.dto.AuthenticatedUserDto;
import com.petcare.be.security.dto.RegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

//	User convertToUser(RegistrationRequest registrationRequest);

	AuthenticatedUserDto convertToAuthenticatedUserDto(User user);

	User convertToUser(AuthenticatedUserDto authenticatedUserDto);
	User convertToUser(RegisterRequest registerRequestDto);

}
