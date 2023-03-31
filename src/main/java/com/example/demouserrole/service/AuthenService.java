package com.example.demouserrole.service;

import com.example.demouserrole.response.LoginResponse;

public interface AuthenService {

    LoginResponse authenticateUser(String userName, String password);

    void registerUser(String userName, String password, String email);
}
