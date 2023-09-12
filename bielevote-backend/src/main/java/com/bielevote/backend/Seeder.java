package com.bielevote.backend;

import com.bielevote.backend.user.User;
import com.bielevote.backend.user.UserRepository;
import com.bielevote.backend.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class Seeder implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) {
        System.out.println("Seeding database...");
        seedUsers();
    }

    private void seedUsers() {
        var citizen1 = User.builder()
                .role(UserRole.CITIZEN)
                .username("citizen")
                .password(passwordEncoder.encode("123"))
                .name("John Smith")
                .phone("123")
                .build();
        userRepository.save(citizen1);
    }
}
