package com.bielevote.backend.project;

import com.bielevote.backend.user.rewardpoint.Transaction;
import com.bielevote.backend.user.rewardpoint.TransactionReason;
import com.bielevote.backend.user.rewardpoint.TransactionRepository;
import com.bielevote.backend.votes.VoteRepository;
import com.bielevote.backend.votes.VoteType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class StatusChecker {
    private final ProjectRepository projectRepository;
    private final VoteRepository voteRepository;
    private final TransactionRepository transactionRepository;

    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS)
    public void checkCompletedDeadlines(@Value("${app.reward-rules.amount-for-accepted}") final int rewardForAccepted) {
        var projects = projectRepository.findByStatusAndEndOfVotingBefore(ProjectStatus.ACTIVE, LocalDateTime.now());
        if (!projects.isEmpty()) {
            System.out.println(LocalDateTime.now() + " --- " + projects.size() + " project(s) have met their deadline.");
            System.out.flush();
        }
        for (Project project : projects) {
            int votesFor = voteRepository.findByProjectAndType(project, VoteType.POSITIVE).size();
            int votesAgainst = voteRepository.findByProjectAndType(project, VoteType.AGAINST).size();
            if (votesFor > votesAgainst) {
                project.setStatus(ProjectStatus.ACCEPTED);
                transactionRepository.saveAndFlush(Transaction.builder()
                        .amount(rewardForAccepted)
                        .date(LocalDateTime.now())
                        .user(project.getAuthor())
                        .reason(TransactionReason.PROJECT_ACCEPTED)
                        .build()
                );
            } else if (votesAgainst > votesFor) {
                project.setStatus(ProjectStatus.REJECTED);
            } else {
                project.setStatus(ProjectStatus.REJECTED);
            }
            projectRepository.saveAndFlush(project);
        }
    }
}
