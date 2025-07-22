package org.example.identityservice.mapper;

import org.example.identityservice.DTO.request.RoleRequest;
import org.example.identityservice.DTO.response.RoleResponse;
import org.example.identityservice.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "permissions", ignore = true)
    Role mapRoleRequestToRole(RoleRequest request);

    RoleResponse mapRoleToRoleResponse(Role role);
}
