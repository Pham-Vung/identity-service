package org.example.identityservice.service.interfaces;

import org.example.identityservice.DTO.request.RoleRequest;
import org.example.identityservice.DTO.response.RoleResponse;

import java.util.List;

public interface IRoleService {
    RoleResponse create(RoleRequest request);

    List<RoleResponse> getAll();

    void delete(String roleName);

    List<RoleResponse> getRolesByPermissionName(String permissionName);
}
