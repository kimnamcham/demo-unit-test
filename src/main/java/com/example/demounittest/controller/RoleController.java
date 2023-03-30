package com.example.demounittest.controller;

import com.example.demounittest.dto.RoleDTO;
import com.example.demounittest.entity.Role;
import com.example.demounittest.request.role.CreateRoleRequest;
import com.example.demounittest.request.role.GetListRoleRequest;
import com.example.demounittest.request.role.UpdateRoleRequest;
import com.example.demounittest.response.BaseItemResponse;
import com.example.demounittest.response.LoginResponse;
import com.example.demounittest.service.RoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles")
public class RoleController extends BaseController {
    private final RoleService roleService;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('CREATE_ROLE','ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody CreateRoleRequest request) {
        RoleDTO response = roleService.createRole(request);
        return buildItemResponse(response);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('UPDATE_ROLE', 'ADMIN')")
    public ResponseEntity<?> update(@Valid @RequestBody UpdateRoleRequest request) {
        RoleDTO response = roleService.updateRole(request);
        return buildItemResponse(response);
    }

    @PostMapping("/list")
    public ResponseEntity<?> getRoles(@Valid @RequestBody GetListRoleRequest request) {
        Page<Role> page = roleService.getAllRoles(request);
        List<RoleDTO> response = page.getContent().stream().map(role -> modelMapper.map(role, RoleDTO.class)).collect(Collectors.toList());
        return buildListItemResponse(response, page.getTotalElements());
    }

}
