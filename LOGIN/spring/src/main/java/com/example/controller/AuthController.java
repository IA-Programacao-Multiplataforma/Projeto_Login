package com.example.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;  
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.controller.adapter.AuthControllerAdapter;
import com.example.controller.adapter.UserControllerAdapter;
import com.example.controller.dto.request.LoginRequest;
import com.example.controller.dto.request.UserRequest;
import com.example.controller.dto.response.AuthResponse; // Usando a sua classe original
import com.example.entity.Login;
import com.example.entity.Token;
import com.example.entity.User;
import com.example.repository.LoginRepository;
import com.example.security.TokenSecurity;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/autenticacao")
public class AuthController {

    private final TokenSecurity tokenSecurity;
    private final LoginRepository repository; 
    
    private static final String ACCESS_TOKEN_COOKIE = "access_token"; 

    public AuthController(TokenSecurity tokenSecurity, LoginRepository repository) {
        this.tokenSecurity = tokenSecurity;
        this.repository = repository;
    }

    @PostMapping("/v1/login")
    public AuthResponse login(@RequestBody LoginRequest request, HttpServletResponse response) {
        Login login = AuthControllerAdapter.cast(request);
        Token token = tokenSecurity.gerarToken(login);

        ResponseCookie cookie = ResponseCookie.from(ACCESS_TOKEN_COOKIE, token.value())
                .httpOnly(true)
                .secure(false) 
                .sameSite("Lax")
                .path("/")
                .maxAge(3600) 
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return new AuthResponse(token.value());
    }

    @PostMapping("/v1/create")
    public AuthResponse create(@RequestBody UserRequest request, HttpServletResponse response) {
        User user = repository.salvar(UserControllerAdapter.cast(request));
        
        Login login = new Login(request.username(), request.password());
        Token token = tokenSecurity.gerarToken(login);

        ResponseCookie cookie = ResponseCookie.from(ACCESS_TOKEN_COOKIE, token.value())
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax") 
                .path("/")
                .maxAge(3600) 
                .build();
        
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        
        return new AuthResponse(token.value());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/v1/logout")
    public void logout(HttpServletResponse response) { 
        ResponseCookie cookie = ResponseCookie.from(ACCESS_TOKEN_COOKIE, "")
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .path("/")
                .maxAge(0) 
                .build();
        
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }
}