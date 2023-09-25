package com.bielevote.backend.project;

import com.bielevote.backend.user.User;
import com.bielevote.backend.user.UserViews;
import com.fasterxml.jackson.annotation.*;
import com.bielevote.backend.votes.Vote;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;

@Jacksonized
@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @JsonView({ProjectViews.GetProjectList.class})
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NonNull
    @JsonView({ProjectViews.GetProjectList.class})
    private String title;

    @NonNull
    @JsonView({ProjectViews.GetProjectList.class})
    @Column(columnDefinition = "CLOB")
    private String summary;

    @JsonView({ProjectViews.Serialize.class})
    @Column(columnDefinition = "CLOB")
    private String content;

    @NonNull
    @JsonView({ProjectViews.GetProjectList.class})
    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonManagedReference
    private User author;

    @NonNull
    @JsonView({ProjectViews.GetProjectList.class})
    @Column(columnDefinition = "TIMESTAMP(0)")
    private LocalDateTime datePublished;

    @Column(columnDefinition = "TIMESTAMP(0)")
    private LocalDateTime startOfVoting;

    @Column(columnDefinition = "TIMESTAMP(0)")
    private LocalDateTime endOfVoting;

    @NonNull
    @JsonView({ProjectViews.GetProjectList.class})
    @Enumerated(value = EnumType.STRING)
    private ProjectStatus status;

    @JsonManagedReference
    @OneToMany(mappedBy = "project")
    private Set<Vote> votes;
}
