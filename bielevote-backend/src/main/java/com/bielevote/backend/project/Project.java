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
@NoArgsConstructor
public class Project {
    @JsonView({ProjectViews.GetProjectList.class, ProjectViews.Serialize.class})
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @JsonView({ProjectViews.GetProjectList.class, ProjectViews.Serialize.class})
    private String title;

    @NonNull
    @JsonView({ProjectViews.GetProjectList.class, ProjectViews.Serialize.class})
    @Column(columnDefinition = "CLOB")
    private String summary;

    @JsonView({ProjectViews.Serialize.class})
    @Column(columnDefinition = "CLOB")
    private String content;

    @NonNull
    @JsonView({ProjectViews.GetProjectList.class, ProjectViews.Serialize.class})
//    @JsonIdentityInfo(property = "username", generator = ObjectIdGenerators.PropertyGenerator.class)
//    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonManagedReference
    private User author;

    @NonNull
    @JsonView({ProjectViews.GetProjectList.class, ProjectViews.Serialize.class})
    @Column(columnDefinition = "TIMESTAMP(0)")
    private LocalDateTime datePublished;

    @NonNull
    @JsonView({ProjectViews.GetProjectList.class, ProjectViews.Serialize.class})
    @Enumerated(value = EnumType.STRING)
    private ProjectStatus status;

    @JsonManagedReference
    @OneToMany(mappedBy = "project")
    private Set<Vote> votes;
}
