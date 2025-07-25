package org.example.identityservice.controller;

import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.example.identityservice.dto.request.AuthenticationRequest;
import org.example.identityservice.dto.request.IntrospectRequest;
import org.example.identityservice.dto.request.LogoutRequest;
import org.example.identityservice.dto.response.ApiResponse;
import org.example.identityservice.dto.response.AuthenticationResponse;
import org.example.identityservice.dto.response.IntrospectResponse;
import org.example.identityservice.service.interfaces.IAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IAuthService authService;

    @PostMapping("/log-ịn")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> authenticate(@RequestBody AuthenticationRequest request) {
        ApiResponse<AuthenticationResponse> response = ApiResponse.<AuthenticationResponse>builder()
                .result(authService.authenticate(request))
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/introspect")
    public ResponseEntity<ApiResponse<IntrospectResponse>> authenticate(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        ApiResponse<IntrospectResponse> response = ApiResponse.<IntrospectResponse>builder()
                .result(authService.introspect(request))
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/log-out")
    public ResponseEntity<ApiResponse<Void>> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authService.logout(request);
        return ResponseEntity.ok(ApiResponse.<Void>builder().build());
    }
}
