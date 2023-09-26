package com.bielevote.backend.project;

import com.bielevote.backend.votes.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class StatusChecker {
    private final ProjectRepository projectRepository;
    private final VoteRepository voteRepository;

    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS)
    public void checkCompletedDeadlines() {
        System.out.println(LocalDateTime.now() + " --- Checking projects");
        System.out.flush();
    }
}
