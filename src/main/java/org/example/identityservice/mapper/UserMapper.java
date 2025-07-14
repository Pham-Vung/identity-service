package org.example.identityservice.mapper;

import org.example.identityservice.DTO.request.UserCreationRequest;
import org.example.identityservice.DTO.request.UserUpdateRequest;
import org.example.identityservice.DTO.response.UserResponse;
import org.example.identityservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User mapUserCreationRequestToUser(UserCreationRequest userCreationRequest);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);

    UserResponse mapUserToUserResponse(User user);
}
