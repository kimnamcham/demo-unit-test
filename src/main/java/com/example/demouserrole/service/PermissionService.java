package com.example.demouserrole.service;

import com.example.demouserrole.entity.Permission;
import com.example.demouserrole.request.permission.GetListPermissionRequest;
import org.springframework.data.domain.Page;

public interface PermissionService {
    Page<Permission> getAllPermission(GetListPermissionRequest request);
}
