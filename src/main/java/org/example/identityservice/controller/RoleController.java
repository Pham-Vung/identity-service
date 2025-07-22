package org.example.identityservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.identityservice.DTO.request.RoleRequest;
import org.example.identityservice.DTO.response.ApiResponse;
import org.example.identityservice.DTO.response.RoleResponse;
import org.example.identityservice.service.interfaces.IRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final IRoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<RoleResponse>> create(@RequestBody RoleRequest request) {
        ApiResponse<RoleResponse> response = ApiResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<List<RoleResponse>>> getAll() {
        List<RoleResponse> roles = roleService.getAll();
        ApiResponse<List<RoleResponse>> response = ApiResponse.<List<RoleResponse>>builder()
                .result(roles)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{roleName}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable String roleName) {
        roleService.delete(roleName);
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .result("Role has been deleted")
                .build());
    }

    @GetMapping("/{permissionName}")
    public ResponseEntity<ApiResponse<List<RoleResponse>>> getRolesByPermissionName(@PathVariable("permissionName") String permissionName) {
        List<RoleResponse> roles = roleService.getRolesByPermissionName(permissionName);
        ApiResponse<List<RoleResponse>> response = ApiResponse.<List<RoleResponse>>builder()
                .result(roles)
                .build();

        return ResponseEntity.ok(response);
    }
}
