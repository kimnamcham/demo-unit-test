package com.example.demounittest.controller;


import javax.validation.Valid;

import com.example.demounittest.constant.ErrorCodeDefs;
import com.example.demounittest.request.auth.LoginRequest;
import com.example.demounittest.request.auth.RegisterRequest;
import com.example.demounittest.response.BaseItemResponse;
import com.example.demounittest.response.BaseResponse;
import com.example.demounittest.response.LoginResponse;
import com.example.demounittest.service.AuthenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthenService authenService;

    @Autowired
    public AuthController(AuthenService authenService) {
        this.authenService = authenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse jwtResponse = authenService.authenticateUser(loginRequest.getUserName(), loginRequest.getPassword());
        BaseItemResponse<LoginResponse> response = new BaseItemResponse<>();
        response.setData(jwtResponse);
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest signUpRequest) {
        BaseResponse response = new BaseResponse();
        try {
            authenService.registerUser(signUpRequest.getUserName(),
                    signUpRequest.getPassword(),
                    signUpRequest.getEmail());
            response.setSuccess(true);
        } catch (Exception ex) {
            response.setFailed(ErrorCodeDefs.SERVER_ERROR, ex.getMessage());
        }
        return ResponseEntity.ok(response);
    }
}
