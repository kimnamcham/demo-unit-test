package com.example.demouserrole.request.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreateUserRequest {
    @Size(min = 3, message = "userName tối thiểu 3 kí tự")
    @NotBlank(message = "userName không được để trống")
    private String userName;
    private String email;
    @Size(min = 6, message = "Mật khẩu tối thiểu 6 kí tự")
    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;
    private Integer roleId;
}
