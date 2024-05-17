package com.example.authservice.configuration;

import com.example.authservice.entity.Role;
import com.example.authservice.model.User;
import com.example.authservice.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultAdminLoader implements ApplicationRunner {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;

    @Override
    public void run(ApplicationArguments args) {
        if (userRepo.findByEmail(System.getenv("ADMIN_EMAIL")).isEmpty()) {
            userRepo.save(User.builder()
                    .name("Admin")
                    .surname("Admin")
                    .email(System.getenv("ADMIN_EMAIL"))
                    .password(encoder.encode(System.getenv("ADMIN_PASSWORD")))
                    .role(Role.ADMIN)
                    .build());
        }
    }
}
