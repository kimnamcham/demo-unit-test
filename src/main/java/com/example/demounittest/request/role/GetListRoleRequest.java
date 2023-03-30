package com.example.demounittest.request.role;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetListRoleRequest {
    @NotNull(message = "Start không được để trống")
    private Integer start;
    @NotNull(message = "Limit không được để trống")
    private Integer limit;
    private String keyword;
}
