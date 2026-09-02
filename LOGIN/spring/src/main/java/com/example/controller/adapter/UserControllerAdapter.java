package com.example.controller.adapter;

import java.util.UUID;

import com.example.controller.dto.request.UserRequest;
import com.example.entity.User;

public class UserControllerAdapter {

    private UserControllerAdapter() {}

    public static User cast(UserRequest request) {
        return new User(
                UUID.randomUUID().toString(),
                request.username(),
                request.password(),
                request.email(),
                request.cep(),
                request.roles()
        );
    }

    public static User castPutRequest(UserRequest request) {
        return new User(
                request.id(), 
                request.username(),
                request.password(),
                request.email(),
                request.cep(),
                request.roles()
        );
    }
}