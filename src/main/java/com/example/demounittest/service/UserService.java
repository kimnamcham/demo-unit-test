package com.example.demounittest.service;

import com.example.demounittest.dto.UserDTO;
import com.example.demounittest.entity.User;
import com.example.demounittest.request.user.CreateUserRequest;
import com.example.demounittest.request.user.GetListUserRequest;
import com.example.demounittest.request.user.UpdateUserRequest;
import org.springframework.data.domain.Page;

public interface UserService {
    UserDTO createUser(CreateUserRequest request);

    UserDTO updateUser(UpdateUserRequest request);

    Page<User> getAllUsers(GetListUserRequest request);
}
