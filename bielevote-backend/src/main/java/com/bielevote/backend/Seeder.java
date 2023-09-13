package com.bielevote.backend;

import com.bielevote.backend.user.User;
import com.bielevote.backend.user.UserRepository;
import com.bielevote.backend.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;


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
        userRepository.saveAllAndFlush(List.of(
                User.builder()
                        .role(UserRole.CITIZEN)
                        .username("citizen1")
                        .password(passwordEncoder.encode("123"))
                        .legalName("John Smith")
                        .phone("123")
                        .build(),
                User.builder()
                        .role(UserRole.ADMINISTRATOR)
                        .username("admin1")
                        .password(passwordEncoder.encode("admin"))
                        .legalName("Jack Admin")
                        .build(),
                User.builder()
                        .role(UserRole.MUNICIPAL)
                        .username("municipal1")
                        .legalName("Jane Doe")
                        .password(passwordEncoder.encode("123"))
                        .build()
        ));
    }
}
