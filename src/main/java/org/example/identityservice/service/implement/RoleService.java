package org.example.identityservice.service.implement;

import lombok.RequiredArgsConstructor;
import org.example.identityservice.dto.request.RoleRequest;
import org.example.identityservice.dto.response.RoleResponse;
import org.example.identityservice.entity.Permission;
import org.example.identityservice.entity.Role;
import org.example.identityservice.mapper.RoleMapper;
import org.example.identityservice.repository.PermissionRepository;
import org.example.identityservice.repository.RoleRepository;
import org.example.identityservice.service.interfaces.IRoleService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RoleMapper roleMapper;

    @Override
    public RoleResponse create(RoleRequest request) {
        Role role = roleMapper.mapRoleRequestToRole(request);

        List<Permission> permissions = permissionRepository.findAllById(request.getPermissions());

        role.setPermissions(new HashSet<>(permissions));

        return roleMapper.mapRoleToRoleResponse(roleRepository.save(role));
    }

    @Override
    public List<RoleResponse> getAll() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::mapRoleToRoleResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String roleName) {
        roleRepository.deleteById(roleName);
    }

    @Override
    public List<RoleResponse> getRolesByPermissionName(String permissionName) {
        return roleRepository.findRolesByPermissionName(permissionName)
                .stream()
                .map(roleMapper::mapRoleToRoleResponse)
                .collect(Collectors.toList());
    }
}
