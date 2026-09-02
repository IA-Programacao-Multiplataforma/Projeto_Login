package com.example.repository.orm;

import com.example.entity.enumerable.UserRole;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.*;

@Document(value = "login")
public record LoginOrmMongo(
        @Id String id,
        String username,
        String password,
        String email,
        String cep,
        List<UserRole> roles
) {
} 