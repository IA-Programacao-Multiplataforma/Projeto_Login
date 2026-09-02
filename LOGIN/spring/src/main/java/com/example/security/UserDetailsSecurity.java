package com.example.security;

import com.example.entity.User;
import com.example.repository.LoginRepository;
import com.example.security.dto.AuthUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsSecurity implements UserDetailsService {

    private final LoginRepository repository;

    public UserDetailsSecurity(LoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User login = repository.findByUsername(username);

        if (login == null) {
            System.out.println(">>> ERRO: USUÁRIO " + username + " NÃO EXISTE NO BANCO! <<<");
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return new AuthUserDetails(login);
    }
}