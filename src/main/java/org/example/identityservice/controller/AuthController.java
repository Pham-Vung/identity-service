package org.example.identityservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.identityservice.DTO.request.AuthenticationRequest;
import org.example.identityservice.DTO.response.ApiResponse;
import org.example.identityservice.DTO.response.AuthenticationResponse;
import org.example.identityservice.service.interfaces.IAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IAuthService authService;

    @PostMapping("/log-ịn")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> authenticate(@RequestBody AuthenticationRequest request) {
        boolean isAuthenticated = authService.authenticate(request);

        ApiResponse<AuthenticationResponse> response = ApiResponse.<AuthenticationResponse>builder()
                .result(AuthenticationResponse.builder()
                        .isAuthenticated(isAuthenticated)
                        .build())
                .build();

        return ResponseEntity.ok(response);
    }
}
