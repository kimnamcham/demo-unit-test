package com.example.demouserrole.exception;

import lombok.Data;

@Data
public class JwtTokenExpired extends RuntimeException {
    public JwtTokenExpired(String message) {
        super(message);
    }
}
