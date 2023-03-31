package com.example.demouserrole.service;

import com.example.demouserrole.dto.UserDTO;
import com.example.demouserrole.entity.User;
import com.example.demouserrole.request.user.CreateUserRequest;
import com.example.demouserrole.request.user.GetListUserRequest;
import com.example.demouserrole.request.user.UpdateUserRequest;
import org.springframework.data.domain.Page;

public interface UserService {
    UserDTO createUser(CreateUserRequest request);

    UserDTO updateUser(UpdateUserRequest request);

    void deleteUser(Long id);

    Page<User> getAllUsers(GetListUserRequest request);
}
