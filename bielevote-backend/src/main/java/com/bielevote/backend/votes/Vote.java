package com.bielevote.backend.votes;

import com.bielevote.backend.project.Project;
import com.bielevote.backend.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Jacksonized
@Table(name = "votes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "project_id"})
})
public class Vote {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VOTES_ID_SEQ")
    @SequenceGenerator(name = "VOTES_ID_SEQ", sequenceName = "VOTES_SEQ", initialValue = 100)
    @Id
    private Long id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @JsonBackReference
    private Project project;

    @NonNull
    @Enumerated(EnumType.STRING)
    private VoteType type;

    @NonNull
    @Column(columnDefinition = "TIMESTAMP(0)")
    private LocalDateTime date;
}
