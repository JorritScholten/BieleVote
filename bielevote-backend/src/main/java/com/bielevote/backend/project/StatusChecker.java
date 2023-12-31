package com.bielevote.backend.project;

import com.bielevote.backend.user.rewardpoint.Transaction;
import com.bielevote.backend.user.rewardpoint.TransactionReason;
import com.bielevote.backend.user.rewardpoint.TransactionRepository;
import com.bielevote.backend.votes.VoteRepository;
import com.bielevote.backend.votes.VoteType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
public class StatusChecker {
    @Autowired
    @Value("${app.reward-rules.amount-for-accepted}")
    int rewardForAccepted;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS)
    public void checkCompletedDeadlines() {
        var projects = projectRepository.findByStatusAndEndOfVotingBefore(ProjectStatus.ACTIVE, LocalDateTime.now());
        if (!projects.isEmpty()) {
            System.out.println(LocalDateTime.now() + " --- " + projects.size() + " project(s) have met their deadline.");
            System.out.flush();
        }
        for (Project project : projects) {
            project.setStatus(ProjectStatus.REVIEW);
            projectRepository.saveAndFlush(project);
        }
    }
}
