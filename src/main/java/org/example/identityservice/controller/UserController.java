package org.example.identityservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.identityservice.DTO.request.UserCreationRequest;
import org.example.identityservice.DTO.request.UserUpdateRequest;
import org.example.identityservice.DTO.response.ApiResponse;
import org.example.identityservice.DTO.response.UserResponse;
import org.example.identityservice.service.interfaces.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<UserResponse> response = ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getUsers() {
        ApiResponse<List<UserResponse>> response = ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserResponse>> getUser(@PathVariable("userId") String id) {
        ApiResponse<UserResponse> response = ApiResponse.<UserResponse>builder()
                .result(userService.getUser(id))
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(@PathVariable("userId") String id, @RequestBody UserUpdateRequest request) {
        ApiResponse<UserResponse> response = ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(id, request))
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable("userId") String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .result("User has been deleted")
                        .build()
        );
    }
}
