package com.bielevote.backend.votes;

import com.bielevote.backend.project.ProjectRepository;
import com.bielevote.backend.user.User;
import com.bielevote.backend.user.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
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

    @PostMapping
    ResponseEntity<Void> performVote(@AuthenticationPrincipal User currentUser,
                                     @Validated @RequestBody(required = true) PostVote postVote) {
        try {
            var vote = Vote.builder()
                    .type(postVote.type)
                    .date(LocalDateTime.now())
                    .user(userRepository.findByUsername(currentUser.getUsername()).orElseThrow())
                    .project(projectRepository.findById(postVote.projectId).orElseThrow())
                    .build();
            voteRepository.save(vote);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    record PostVote(Long projectId, VoteType type) {
    }
}
