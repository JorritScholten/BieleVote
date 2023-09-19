package com.bielevote.backend.project;

import com.bielevote.backend.user.User;
import com.bielevote.backend.user.UserViews;
import com.bielevote.backend.votes.Vote;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
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
@JsonView(UserViews.getProject.class)
public class Project {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    @Column(columnDefinition = "CLOB")
    private String content;
    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonManagedReference
    private User author;
    @Column(columnDefinition = "TIMESTAMP(0)")
    private LocalDateTime datePublished;
    @Enumerated(value = EnumType.STRING)
    private ProjectStatus status;
    @JsonManagedReference
    @OneToMany(mappedBy = "project")
    private Set<Vote> votes;
}
