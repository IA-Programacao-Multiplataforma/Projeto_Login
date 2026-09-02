package com.example.entity;

import java.util.List;

import com.example.entity.enumerable.UserRole;

public record User(
        String id,
        String username,
        String password,
        String email,
        String cep,
        List<UserRole> roles
) {
}
