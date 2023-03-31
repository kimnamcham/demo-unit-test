package com.example.demouserrole.controller;

import com.example.demouserrole.dto.UserDTO;
import com.example.demouserrole.entity.User;
import com.example.demouserrole.request.user.CreateUserRequest;
import com.example.demouserrole.request.user.GetListUserRequest;
import com.example.demouserrole.request.user.UpdateUserRequest;
import com.example.demouserrole.response.BaseResponse;
import com.example.demouserrole.service.UserService;
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
@RequestMapping("/api/users")
public class UserController extends BaseController {
    private final UserService userService;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('CREATE_USER','ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody CreateUserRequest request) {
        UserDTO response = userService.createUser(request);
        return buildItemResponse(response);
    }

    @PutMapping("")
    @PreAuthorize("hasAnyAuthority('UPDATE_USER', 'ADMIN')")
    public ResponseEntity<?> update(@Valid @RequestBody UpdateUserRequest request) {
        UserDTO response = userService.updateUser(request);
        return buildItemResponse(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('UPDATE_ROLE', 'ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        BaseResponse response = new BaseResponse();
        userService.deleteUser(id);
        response.setSuccess(true);
        return buildItemResponse(response);
    }

    @PostMapping("/list")
    public ResponseEntity<?> getList(@Valid @RequestBody GetListUserRequest request) {
        Page<User> page = userService.getAllUsers(request);
        List<UserDTO> response = page.getContent().stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        return buildListItemResponse(response, page.getTotalElements());
    }
}
