package com.example.demouserrole.controller;

import com.example.demouserrole.dto.PermissionDTO;
import com.example.demouserrole.entity.Permission;
import com.example.demouserrole.request.permission.GetListPermissionRequest;
import com.example.demouserrole.service.PermissionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController extends BaseController {
    private final PermissionService permissionService;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping("/list")
    public ResponseEntity<?> getRoles(@Valid @RequestBody GetListPermissionRequest request) {
        Page<Permission> page = permissionService.getAllPermission(request);
        List<PermissionDTO> response = page.getContent().stream()
                .map(permission -> modelMapper.map(permission, PermissionDTO.class))
                .collect(Collectors.toList());
        return buildListItemResponse(response, page.getTotalElements());
    }

}
