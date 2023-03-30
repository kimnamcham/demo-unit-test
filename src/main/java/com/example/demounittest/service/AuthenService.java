package com.example.demounittest.service;

import com.example.demounittest.response.LoginResponse;

public interface AuthenService {

    LoginResponse authenticateUser(String userName, String password);

    void registerUser(String userName, String password, String email);
}
