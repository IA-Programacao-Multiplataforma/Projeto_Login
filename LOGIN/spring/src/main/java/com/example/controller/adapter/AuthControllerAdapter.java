package com.example.controller.adapter;

import com.example.controller.dto.request.LoginRequest;
import com.example.entity.Login;

public class AuthControllerAdapter {
    private AuthControllerAdapter() {}

    public static Login cast(LoginRequest request) {
        return new Login(request.username(), request.password());
    }
}
