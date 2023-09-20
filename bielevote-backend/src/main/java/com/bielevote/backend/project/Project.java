package com.bielevote.backend.project;

import com.bielevote.backend.user.User;
import com.bielevote.backend.user.UserViews;
import com.fasterxml.jackson.annotation.*;
import com.bielevote.backend.votes.Vote;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.Set;

@Jacksonized
@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Project {
    @JsonView(ProjectViews.GetProjectList.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonView(ProjectViews.GetProjectList.class)
    private String title;

    @JsonView(ProjectViews.GetProjectList.class)
    @Column(columnDefinition = "CLOB")
    private String summary;

    @Column(columnDefinition = "CLOB")
    private String content;

    @JsonView(ProjectViews.GetProjectList.class)
    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonManagedReference
    private User author;

    @JsonView(ProjectViews.GetProjectList.class)
    @Column(columnDefinition = "TIMESTAMP(0)")
    private LocalDateTime datePublished;

    @JsonView(ProjectViews.GetProjectList.class)
    @Enumerated(value = EnumType.STRING)
    private ProjectStatus status;

    @JsonManagedReference
    @OneToMany(mappedBy = "project")
    private Set<Vote> votes;
}
