package com.bielevote.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Seeder implements CommandLineRunner {
    @Override
    public void run(String... args) {
        System.out.println("Seeding database...");
    }
}