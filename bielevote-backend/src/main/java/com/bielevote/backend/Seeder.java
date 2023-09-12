package com.bielevote.backend;

import com.bielevote.backend.user.User;
import com.bielevote.backend.user.UserRepository;
import com.bielevote.backend.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class Seeder implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    @Value("${app.encrypted-password-storage}")
    private Boolean encryptPasswords;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) {
        System.out.println("Seeding database...");
        seedUsers();
    }

    private void seedUsers() {
        var citizen1 = User.builder();
        citizen1.role(UserRole.CITIZEN);
        citizen1.username("citizen");
        if (encryptPasswords) {
            citizen1.password(passwordEncoder.encode("123"));
        } else {
            citizen1.password("123");
        }
        citizen1.name("John Smith");
        citizen1.phone("123");
        userRepository.save(citizen1.build());
    }
}
