package org.example.identityservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.identityservice.DTO.request.PermissionRequest;
import org.example.identityservice.DTO.response.ApiResponse;
import org.example.identityservice.DTO.response.PermissionResponse;
import org.example.identityservice.service.interfaces.IPermissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
public class PermissionController {
    private final IPermissionService permissionService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<PermissionResponse>> create(@RequestBody PermissionRequest request) {
        ApiResponse<PermissionResponse> response = ApiResponse.<PermissionResponse>builder()
                .result(permissionService.create(request))
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<List<PermissionResponse>>> getAll() {
        ApiResponse<List<PermissionResponse>> response = ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getAll())
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{permissionName}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable String permissionName) {
        permissionService.delete(permissionName);
        ApiResponse<String> response = ApiResponse.<String>builder()
                .result("Permission has been deleted")
                .build();

        return ResponseEntity.ok(response);
    }
}
