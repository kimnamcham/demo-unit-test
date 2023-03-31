package com.example.demouserrole.service;

import com.example.demouserrole.entity.Permission;
import com.example.demouserrole.repository.CustomPermissionRepository;
import com.example.demouserrole.repository.PermissionRepository;
import com.example.demouserrole.request.permission.GetListPermissionRequest;
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
