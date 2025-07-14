package org.example.identityservice.service.interfaces;

import org.example.identityservice.DTO.request.AuthenticationRequest;

public interface IAuthService {
    boolean authenticate(AuthenticationRequest request);
}
