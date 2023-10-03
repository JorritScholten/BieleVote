package com.bielevote.backend.project;

import com.bielevote.backend.user.User;
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
@NoArgsConstructor
public class Project {
    @JsonView({ProjectViews.GetProjectList.class})
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROJECT_ID_SEQ")
    @SequenceGenerator(name = "PROJECT_ID_SEQ", sequenceName = "PROJECT_SEQ", initialValue = 1, allocationSize = 1)
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
