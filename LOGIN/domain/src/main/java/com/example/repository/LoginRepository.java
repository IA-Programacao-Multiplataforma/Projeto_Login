package com.example.repository;

import com.example.entity.User;

public interface LoginRepository {
    User salvar(User user);
    void deletar(String id);

    User findByUsername(String username);
    User findByEmail(String email);

}
