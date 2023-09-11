package com.bielevote.backend;

import com.bielevote.backend.user.User;
import com.bielevote.backend.user.UserRepository;
import com.bielevote.backend.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Seeder implements CommandLineRunner {
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
                .password("123")
                .name("John Smith")
                .phone("123")
                .build();
        userRepository.save(citizen1);
    }
}
