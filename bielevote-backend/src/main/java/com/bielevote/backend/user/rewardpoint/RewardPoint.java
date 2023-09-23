package com.bielevote.backend.user.rewardpoint;

import com.bielevote.backend.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Jacksonized
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "REWARD_POINT_TRANSACTIONS")
public class RewardPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private Integer amount;

    @NonNull
    @Column(columnDefinition = "TIMESTAMP(0)")
    private LocalDateTime date;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @NonNull
    @Enumerated(value = EnumType.STRING)
    private TransactionReason reason;

    @JsonIgnore
    @Transient
    public static final int AMOUNT_FOR_VOTE = 5;
    @JsonIgnore
    @Transient
    public static final int AMOUNT_FOR_PROJECT_ACCEPTED = 100;
}
