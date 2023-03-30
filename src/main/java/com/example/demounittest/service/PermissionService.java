package com.example.demounittest.service;

import com.example.demounittest.entity.Permission;
import com.example.demounittest.request.permission.GetListPermissionRequest;
import org.springframework.data.domain.Page;

public interface PermissionService {
    Page<Permission> getAllPermission(GetListPermissionRequest request);
}
