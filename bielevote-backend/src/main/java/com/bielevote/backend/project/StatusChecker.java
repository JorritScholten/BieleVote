package com.bielevote.backend.project;

import com.bielevote.backend.votes.VoteRepository;
import com.bielevote.backend.votes.VoteType;
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

        var projects = projectRepository.findByStatus(ProjectStatus.ACTIVE);
        for (Project project : projects) {
            if (project.getEndOfVoting().isBefore(LocalDateTime.now())) {
                int votesFor = voteRepository.findByProjectAndType(project, VoteType.POSITIVE).size();
                int votesAgainst = voteRepository.findByProjectAndType(project, VoteType.AGAINST).size();
                if (votesFor > votesAgainst) {
                    project.setStatus(ProjectStatus.ACCEPTED);
                } else if (votesAgainst > votesFor) {
                    project.setStatus(ProjectStatus.REJECTED);
                } else {
                    project.setStatus(ProjectStatus.REJECTED);
                }
                projectRepository.saveAndFlush(project);
            }
        }
    }
}
