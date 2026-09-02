package com.example.repository.adapter;

import com.example.entity.User;
import com.example.repository.orm.LoginOrmMongo;

public class LoginRepositoryAdapter {
    private LoginRepositoryAdapter() {}

    public static User castOrm(LoginOrmMongo orm) {
        return new User(orm.id(), orm.username(), orm.password(), orm.email(), orm.cep(), orm.roles());
    }

    public static LoginOrmMongo castEntity(User entity) {
        return new LoginOrmMongo(entity.id(), entity.username(), entity.password(), entity.email(), entity.cep(), entity.roles());
    }
}