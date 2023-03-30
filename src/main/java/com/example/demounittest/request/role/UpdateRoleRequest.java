package com.example.demounittest.request.role;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UpdateRoleRequest {
    @NotNull(message = "id không được để trống")
    private Integer id;
    private String name;
    private List<String> permissionIds;
}
