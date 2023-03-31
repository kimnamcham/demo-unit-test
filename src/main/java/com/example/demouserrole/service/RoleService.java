package com.example.demouserrole.service;

import com.example.demouserrole.dto.RoleDTO;
import com.example.demouserrole.entity.Role;
import com.example.demouserrole.request.role.CreateRoleRequest;
import com.example.demouserrole.request.role.GetListRoleRequest;
import com.example.demouserrole.request.role.UpdateRoleRequest;
import org.springframework.data.domain.Page;

public interface RoleService {
    RoleDTO createRole(CreateRoleRequest createRoleRequest);

    RoleDTO updateRole(UpdateRoleRequest updateRoleRequest);

    Page<Role> getAllRoles(GetListRoleRequest request);
}
