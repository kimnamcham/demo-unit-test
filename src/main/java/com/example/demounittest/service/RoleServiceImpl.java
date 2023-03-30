package com.example.demounittest.service;

import com.example.demounittest.dto.RoleDTO;
import com.example.demounittest.entity.Permission;
import com.example.demounittest.entity.Role;
import com.example.demounittest.repository.CustomRoleRepository;
import com.example.demounittest.repository.PermissionRepository;
import com.example.demounittest.repository.RoleRepository;
import com.example.demounittest.request.role.CreateRoleRequest;
import com.example.demounittest.request.role.GetListRoleRequest;
import com.example.demounittest.request.role.UpdateRoleRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository,
                           PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public RoleDTO createRole(CreateRoleRequest createRoleRequest) {
        checkPermissionIsValid(createRoleRequest.getPermissionIds());
        Role role = Role.builder()
                .name(createRoleRequest.getName())
                .build();
        List<Permission> permissions = buildPermission(createRoleRequest.getPermissionIds());
        role.setPermissions(permissions);

        roleRepository.save(role);
        return modelMapper.map(role, RoleDTO.class);
    }

    @Override
    public RoleDTO updateRole(UpdateRoleRequest updateRoleRequest) {
        checkPermissionIsValid(updateRoleRequest.getPermissionIds());
        validateRoleExist(updateRoleRequest.getId());
        Optional<Role> role = roleRepository.findById(updateRoleRequest.getId());
        if (role.isPresent()) {
            List<Permission> permissions = buildPermission(updateRoleRequest.getPermissionIds());
            Role roleUpdatePojo = role.get();
            roleUpdatePojo.setName(updateRoleRequest.getName());
            roleUpdatePojo.setPermissions(permissions);
            return modelMapper.map(roleRepository.save(roleUpdatePojo), RoleDTO.class);
        }
        throw new RuntimeException("Có lỗi xảy ra trong quá trình update vai trò");
    }

    @Override
    public Page<Role> getAllRoles(GetListRoleRequest request) {
        Specification<Role> specification = CustomRoleRepository.buildFilterSpecification(request.getKeyword());
        return roleRepository.findAll(specification, PageRequest.of(request.getStart(), request.getLimit()));
    }

    private void validateRoleExist(Integer id) {
        boolean isExist = roleRepository.existsById(id);
        if (!isExist)
            throw new RuntimeException("Vai trò không tồn tại trên hệ thống");
    }

    private void checkPermissionIsValid(List<String> permissionIds) {
        List<Permission> permissions = buildPermission(permissionIds);
        if (CollectionUtils.isEmpty(permissions)) {
            throw new RuntimeException("Permisison không tồn tại");
        }
        List<String> listIdExists = permissions.stream().map(Permission::getId).collect(Collectors.toList());
        List<String> idNotExists = permissionIds.stream().filter(s -> !listIdExists.contains(s)
        ).collect(Collectors.toList());
        if (!idNotExists.isEmpty())
            throw new RuntimeException(String.format("Trong danh sách permisison ids có mã không tồn tại trên hệ thống: %s", idNotExists));
    }

    private List<Permission> buildPermission(List<String> permissionIds) {
        return permissionRepository.findAllById(permissionIds);
    }
}
