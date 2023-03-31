package com.example.demouserrole.request.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetListUserRequest {
    @NotNull(message = "Start không được để trống")
    private Integer start;
    @NotNull(message = "Limit không được để trống")
    private Integer limit;
    private String keyword;
}
