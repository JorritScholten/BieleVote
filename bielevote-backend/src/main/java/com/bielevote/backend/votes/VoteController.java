package com.bielevote.backend.votes;

import com.bielevote.backend.project.ProjectRepository;
import com.bielevote.backend.user.User;
import com.bielevote.backend.user.UserRepository;
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

    @GetMapping("/{id}")
    ResponseEntity<Boolean> hasVoted(@AuthenticationPrincipal User currentUser, @PathVariable("id") long id) {
        try {
            var user = userRepository.findByUsername(currentUser.getUsername()).orElseThrow();
            var project = projectRepository.findById(id).orElseThrow();
            return ResponseEntity.ok(voteRepository.findByUserAndProject(user, project).isPresent());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

//    @PostMapping("/{id}")
//    ResponseEntity<Void> performVote(@AuthenticationPrincipal User currentUser, @PathVariable("id") long id) {
//        try {
//            var vote = Vote.builder()
//                    .type(postVote.type)
//                    .date(LocalDateTime.now())
//                    .user(userRepository.findByUsername(currentUser.getUsername()).orElseThrow())
//                    .project(projectRepository.findById(postVote.projectId).orElseThrow())
//                    .build();
//            voteRepository.save(vote);
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        } catch (NoSuchElementException e) {
//            return ResponseEntity.notFound().build();
//        } catch (RuntimeException e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }

    record PostVoteDTO(Long projectId, VoteType type) {
    }
}
