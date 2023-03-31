package com.example.demouserrole.exception;

import lombok.Data;

@Data
public class JwtTokenInvalid extends RuntimeException {
    public JwtTokenInvalid(String message) {
        super(message);
    }
}
