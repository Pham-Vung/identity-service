package org.example.identityservice.mapper;

import org.example.identityservice.dto.request.PermissionRequest;
import org.example.identityservice.dto.response.PermissionResponse;
import org.example.identityservice.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission mapPerssionRequestToPermission(PermissionRequest request);

    PermissionResponse mapPermissionToPermissionResponse(Permission permission);
}
