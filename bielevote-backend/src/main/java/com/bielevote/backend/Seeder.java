package com.bielevote.backend;

import com.bielevote.backend.news.NewsArticleRepository;
import com.bielevote.backend.project.ProjectRepository;
import com.bielevote.backend.reward_shop.RewardRepository;
import com.bielevote.backend.user.UserRepository;
import com.bielevote.backend.user.rewardpoint.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;


@RequiredArgsConstructor
@Component
public class Seeder implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final NewsArticleRepository newsArticleRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final ProjectRepository projectRepository;
    private final RewardRepository rewardRepository;

    @Override
    public void run(String... args) throws IOException {
//        System.out.println("Seeding database...");
//        seedUsers();
        seedProjects();
//        seedNewsArticles();
//        seedRewards();
    }

    private void seedUsers() {
    }

    private void seedProjects() {
        var project = projectRepository.findById(2L).orElseThrow();
        project.setStartOfVoting(LocalDateTime.now().minusWeeks(3));
        project.setEndOfVoting(LocalDateTime.now().plusWeeks(1));
        projectRepository.saveAndFlush(project);
    }

    private void seedNewsArticles() {
    }

    private void seedRewards() {
    }
}
