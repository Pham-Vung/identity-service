package org.example.identityservice.service.interfaces;

import org.example.identityservice.DTO.request.PermissionRequest;
import org.example.identityservice.DTO.response.PermissionResponse;

import java.util.List;

public interface IPermissionService {
    PermissionResponse create(PermissionRequest request);

    List<PermissionResponse> getAll();

    void delete(String permissionName);
}
