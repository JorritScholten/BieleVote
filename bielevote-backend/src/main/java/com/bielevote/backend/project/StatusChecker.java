package com.bielevote.backend.project;

import com.bielevote.backend.votes.VoteRepository;
import com.bielevote.backend.votes.VoteType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class StatusChecker {
    private final ProjectRepository projectRepository;
    private final VoteRepository voteRepository;

    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS)
    public void checkCompletedDeadlines() {
        System.out.println("checking projects");
    }
}
