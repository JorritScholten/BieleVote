package com.bielevote.backend;

import com.bielevote.backend.news.NewsArticleRepository;
import com.bielevote.backend.project.Project;
import com.bielevote.backend.project.ProjectRepository;
import com.bielevote.backend.project.ProjectStatus;
import com.bielevote.backend.reward_shop.RewardRepository;
import com.bielevote.backend.user.UserRepository;
import com.bielevote.backend.user.accountrequests.AccountRequest;
import com.bielevote.backend.user.accountrequests.AccountRequestRepository;
import com.bielevote.backend.user.rewardpoint.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@Component
public class Seeder implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final NewsArticleRepository newsArticleRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final ProjectRepository projectRepository;
    private final RewardRepository rewardRepository;
    private final AccountRequestRepository accountRequestRepository;

    @Override
    public void run(String... args) throws IOException {
//        System.out.println("Seeding database...");
//        seedUsers();
//        seedProjects();
//        seedNewsArticles();
//        seedRewards();
        seedAccountRequests();
    }

    private void seedAccountRequests() {
        accountRequestRepository.saveAllAndFlush(List.of(
                AccountRequest.builder()
                        .username("berendjan")
                        .legalName("Berend-Jan de Jong")
                        .phone("0631245121")
                        .dateRequested(LocalDateTime.now().minusDays(1))
                        .build(),
                AccountRequest.builder()
                        .username("henklol")
                        .legalName("Henk Koe")
                        .phone("0631245122")
                        .dateRequested(LocalDateTime.now().minusDays(2))
                        .build()
        ));
    }

    private void seedUsers() {
    }

    private void seedProjects() {
        projectRepository.saveAllAndFlush(List.of(
                Project.builder()
                        .author(userRepository.findByUsername("admin1").orElseThrow())
                        .datePublished(LocalDateTime.now())
                        .title("should be rejected")
                        .summary("")
                        .content("")
                        .status(ProjectStatus.ACTIVE)
                        .startOfVoting(LocalDateTime.now())
                        .endOfVoting(LocalDateTime.now().plusSeconds(25))
                        .build(),
                Project.builder()
                        .author(userRepository.findByUsername("admin1").orElseThrow())
                        .datePublished(LocalDateTime.now())
                        .title("should also be rejected")
                        .summary("")
                        .content("")
                        .status(ProjectStatus.ACTIVE)
                        .startOfVoting(LocalDateTime.now())
                        .endOfVoting(LocalDateTime.now().plusSeconds(69))
                        .build()
        ));
    }

    private void seedNewsArticles() {
    }

    private void seedRewards() {
    }
}
