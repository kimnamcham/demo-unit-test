package com.example.demouserrole.controller;

import com.example.demouserrole.response.BaseItemResponse;
import com.example.demouserrole.response.TestResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    public BaseItemResponse allAccess() {
        TestResponse response = new TestResponse("Public Content.");
        return new BaseItemResponse<>(true, null, response);
    }
//    @GetMapping("/user")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
//    public String userAccess() {
//        return "User Content.";
//    }

    @GetMapping("/mod")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public BaseItemResponse moderatorAccess() {
        TestResponse response = new TestResponse("Moderator Board.");
        return new BaseItemResponse<>(true, null, response);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public BaseItemResponse adminAccess() {
        TestResponse response = new TestResponse("Admin Board.");
        return new BaseItemResponse<>(true, null, response);
    }

    @GetMapping("/products/list")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public BaseItemResponse productList() {
        TestResponse response = new TestResponse("Admin product list.");
        return new BaseItemResponse<>(true, null, response);
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyAuthority('USER')")
    public BaseItemResponse userAcess() {
        TestResponse response = new TestResponse("User Board.");
        return new BaseItemResponse<>(true, null, response);
    }
}