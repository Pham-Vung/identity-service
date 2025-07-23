package org.example.identityservice.service.implement;

import lombok.RequiredArgsConstructor;
import org.example.identityservice.dto.request.PermissionRequest;
import org.example.identityservice.dto.response.PermissionResponse;
import org.example.identityservice.entity.Permission;
import org.example.identityservice.entity.Role;
import org.example.identityservice.mapper.PermissionMapper;
import org.example.identityservice.repository.PermissionRepository;
import org.example.identityservice.repository.RoleRepository;
import org.example.identityservice.service.interfaces.IPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PermissionService implements IPermissionService {
    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;
    private final RoleRepository roleRepository;

    @Override
    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.mapPerssionRequestToPermission(request);

        return permissionMapper.mapPermissionToPermissionResponse(permissionRepository.save(permission));
    }

    @Override
    public List<PermissionResponse> getAll() {
        return permissionRepository.findAll()
                .stream()
                .map(permissionMapper::mapPermissionToPermissionResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String permissionName) {
        List<Role> roles = roleRepository.findRolesByPermissionName(permissionName);

        for (Role role : roles) {
            role.getPermissions().removeIf(permission -> permission.getName().equals(permissionName));
            roleRepository.save(role);
        }

        permissionRepository.deleteById(permissionName);
    }

}
