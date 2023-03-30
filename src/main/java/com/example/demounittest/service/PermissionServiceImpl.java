package com.example.demounittest.service;

import com.example.demounittest.entity.Permission;
import com.example.demounittest.repository.CustomPermissionRepository;
import com.example.demounittest.repository.PermissionRepository;
import com.example.demounittest.request.permission.GetListPermissionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Page<Permission> getAllPermission(GetListPermissionRequest request) {
        Specification<Permission> specification = CustomPermissionRepository.buildFilterSpecification(request.getKeyword());
        return permissionRepository.findAll(specification, PageRequest.of(request.getStart(), request.getLimit()));
    }
}
