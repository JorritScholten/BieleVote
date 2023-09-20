package com.bielevote.backend.votes;

import com.bielevote.backend.project.Project;
import com.bielevote.backend.user.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findByUserAndProject(@NonNull User user, @NonNull Project project);
}
