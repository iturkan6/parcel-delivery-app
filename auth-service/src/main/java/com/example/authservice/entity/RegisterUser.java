package com.example.authservice.entity;

public record RegisterUser(
        String name,
        String surname,
        String email,
        String password,
        String role) {
}
