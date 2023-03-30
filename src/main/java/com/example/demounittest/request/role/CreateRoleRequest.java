package com.example.demounittest.request.role;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreateRoleRequest {
    private String name;
    private List<String> permissionIds;
}
