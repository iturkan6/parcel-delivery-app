package com.example.authservice.service;

import com.example.authservice.entity.AuthResponse;
import com.example.authservice.entity.LoginUser;
import com.example.authservice.entity.RegisterUser;
import com.example.authservice.entity.Role;
import com.example.authservice.model.User;
import com.example.authservice.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager manager;

    public AuthResponse register(RegisterUser request) {
        User user = User.builder()
                .name(request.name())
                .surname(request.surname())
                .email(request.email())
                .password(encoder.encode(request.password()))
                .role(canRegister(request.role()))
                .build();
        userRepo.save(user);
        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginUser request) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                ));
        var user = userRepo.findByEmail(request.email()).orElseThrow();
        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }

    public Role canRegister(String role) {
        if (!role.equals(Role.USER.name())) {
            throw new AccessDeniedException("Only user can be registered");
        }
        return Role.valueOf(role);
    }
}
