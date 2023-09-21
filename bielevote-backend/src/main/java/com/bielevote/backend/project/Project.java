package com.bielevote.backend.project;

import com.bielevote.backend.user.User;
import com.bielevote.backend.user.UserViews;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Jacksonized
@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@JsonView({UserViews.getProject.class, ProjectViews.Serialize.class})
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "CLOB")
    private String summary;

    @Column(columnDefinition = "CLOB")
    private String content;

    @JsonIdentityInfo(property = "username", generator = ObjectIdGenerators.PropertyGenerator.class)
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonManagedReference
    private User author;

    @Column(columnDefinition = "TIMESTAMP(0)")
    private LocalDateTime datePublished;

    @Enumerated(value = EnumType.STRING)
    private ProjectStatus status;
}
