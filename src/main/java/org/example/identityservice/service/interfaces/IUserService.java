package org.example.identityservice.service.interfaces;

import org.example.identityservice.DTO.request.UserCreationRequest;
import org.example.identityservice.DTO.request.UserUpdateRequest;
import org.example.identityservice.entity.User;

import java.util.List;

public interface IUserService {
    User createUser(UserCreationRequest request);

    List<User> getUsers();

    User getUser(String id);

    User updateUser(String userId, UserUpdateRequest request);

    void deleteUser(String userId);
}
