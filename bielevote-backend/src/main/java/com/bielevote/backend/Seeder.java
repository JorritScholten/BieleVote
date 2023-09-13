package com.bielevote.backend;

import com.bielevote.backend.project.Project;
import com.bielevote.backend.project.ProjectRepository;
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
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public void run(String... args) {
        System.out.println("Seeding database...");
        seedUsers();
        seedProjects();
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

    private void seedProjects() {
        long count = projectRepository.count();
        if (count == 0) {
            List<Project> projects = List.of(
                    Project.builder()
                            .title("Park")
                            .content("new park")
                            .build(),
                    Project.builder()
                            .title("Swimming pool")
                            .content("new swimming pool")
                            .build()
            );
            projectRepository.saveAll(projects);
            count = projectRepository.count();
        }
        System.out.println(count + " projects seeded");
    }
}
