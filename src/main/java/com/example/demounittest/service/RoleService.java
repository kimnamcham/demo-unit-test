package com.example.demounittest.service;

import com.example.demounittest.dto.RoleDTO;
import com.example.demounittest.entity.Role;
import com.example.demounittest.request.role.CreateRoleRequest;
import com.example.demounittest.request.role.GetListRoleRequest;
import com.example.demounittest.request.role.UpdateRoleRequest;
import org.springframework.data.domain.Page;

public interface RoleService {
    RoleDTO createRole(CreateRoleRequest createRoleRequest);

    RoleDTO updateRole(UpdateRoleRequest updateRoleRequest);

    Page<Role> getAllRoles(GetListRoleRequest request);
}
