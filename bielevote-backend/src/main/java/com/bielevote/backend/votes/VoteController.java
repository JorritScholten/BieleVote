package com.bielevote.backend.votes;

import com.bielevote.backend.project.ProjectRepository;
import com.bielevote.backend.project.ProjectStatus;
import com.bielevote.backend.user.User;
import com.bielevote.backend.user.UserRepository;
import com.bielevote.backend.user.rewardpoint.RewardPoint;
import com.bielevote.backend.user.rewardpoint.RewardPointRepository;
import com.bielevote.backend.user.rewardpoint.TransactionReason;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/votes")
public class VoteController {
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final RewardPointRepository rewardPointRepository;

    @GetMapping("/{id}")
    ResponseEntity<Boolean> hasVoted(@AuthenticationPrincipal User currentUser, @PathVariable("id") long projectId) {
        try {
            var user = userRepository.findByUsername(currentUser.getUsername()).orElseThrow();
            var project = projectRepository.findById(projectId).orElseThrow();
            return ResponseEntity.ok(voteRepository.findByUserAndProject(user, project).isPresent());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/{id}")
    ResponseEntity<Void> performVote(@AuthenticationPrincipal User currentUser, @PathVariable("id") long projectId,
                                     @RequestHeader(name = "voteType") String voteType) {
        try {
            var vote = Vote.builder()
                    .type(VoteType.valueOf(voteType))
                    .date(LocalDateTime.now())
                    .user(userRepository.findByUsername(currentUser.getUsername()).orElseThrow())
                    .project(projectRepository.findById(projectId).orElseThrow())
                    .build();
            if (vote.getProject().getStatus() != ProjectStatus.ACTIVE) {
                throw new IllegalArgumentException();
            }
            voteRepository.save(vote);
            rewardPointRepository.save(RewardPoint.builder()
                    .amount(RewardPoint.AMOUNT_FOR_VOTE)
                    .reason(TransactionReason.VOTED_ON_PROJECT)
                    .date(LocalDateTime.now())
                    .user(vote.getUser())
                    .build()
            );
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
