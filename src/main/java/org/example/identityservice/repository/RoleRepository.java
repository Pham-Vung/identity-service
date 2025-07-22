package org.example.identityservice.repository;

import org.example.identityservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String> {

    @Query(value = "SELECT r.* FROM roles r " +
            "JOIN roles_permissions rp ON r.name = rp.role_name " +
            "JOIN permissions p ON p.name = rp.permissions_name " +
            "WHERE p.name = :permissionName", nativeQuery = true)
    List<Role> findRolesByPermissionName(@Param("permissionName") String permissionName);
}