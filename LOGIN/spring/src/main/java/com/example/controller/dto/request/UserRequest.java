package com.example.controller.dto.request;

import java.util.List;

import com.example.entity.enumerable.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UserRequest(
        @JsonProperty("id") String id, 
        @JsonProperty("username") String username,
        @JsonProperty("password") String password,
        @JsonProperty("email") String email,
        @JsonProperty("cep") String cep,
        @JsonProperty("roles") List<UserRole> roles) {
}