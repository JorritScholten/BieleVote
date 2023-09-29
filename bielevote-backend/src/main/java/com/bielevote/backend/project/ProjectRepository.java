package com.bielevote.backend.project;

import com.bielevote.backend.user.User;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findByStatusIn(Collection<ProjectStatus> statuses, Pageable pageable);

    List<Project> findByStatus(ProjectStatus status);

    List<Project> findByStatusAndEndOfVotingBefore(ProjectStatus status, LocalDateTime now);

    Long countByAuthorAndDatePublishedAfter(@NonNull User author, @NonNull LocalDateTime oneMonthAgo);

    List<Project> findByAuthorAndDatePublishedAfter(@NonNull User author, @NonNull LocalDateTime oneMonthAgo);
}
