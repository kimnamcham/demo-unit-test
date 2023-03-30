package com.example.demounittest.request.permission;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetListPermissionRequest {
    @NotNull(message = "Start không được để trống")
    private Integer start;
    @NotNull(message = "Limit không được để trống")
    private Integer limit;
    private String keyword;
}
