package org.example.identityservice.service.interfaces;

import org.example.identityservice.DTO.request.UserCreationRequest;
import org.example.identityservice.DTO.request.UserUpdateRequest;
import org.example.identityservice.DTO.response.UserResponse;

import java.util.List;

public interface IUserService {
    UserResponse createUser(UserCreationRequest request);

    List<UserResponse> getUsers();

    UserResponse getUser(String id);

    UserResponse updateUser(String userId, UserUpdateRequest request);

    void deleteUser(String userId);

    UserResponse getMyInfo();
}
