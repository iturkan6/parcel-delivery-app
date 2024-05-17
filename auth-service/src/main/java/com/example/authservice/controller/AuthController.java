package com.example.authservice.controller;

import com.example.authservice.entity.AuthResponse;
import com.example.authservice.entity.LoginUser;
import com.example.authservice.entity.RegisterUser;
import com.example.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
//@SecurityRequirement(name = "/api/v1/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(
            @RequestBody RegisterUser request
    ){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authentication")
    public ResponseEntity<AuthResponse> loginUser(
            @RequestBody LoginUser request
    ){
        return ResponseEntity.ok(service.login(request));
    }
}
