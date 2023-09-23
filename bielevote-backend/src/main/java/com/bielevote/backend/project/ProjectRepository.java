package com.bielevote.backend.project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findByStatusIn(Collection<ProjectStatus> statuses, Pageable pageable);
}
