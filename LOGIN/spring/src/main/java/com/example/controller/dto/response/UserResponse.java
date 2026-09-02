package com.example.controller.dto.response;

import com.example.entity.enumerable.UserRole;
import java.util.List;  
public record UserResponse(
        String id,
        String username,
        String password,
        String email,
        String cep,
        List<UserRole> roles
    ) {
    
}