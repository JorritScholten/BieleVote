package com.bielevote.backend.user.rewardpoint;

import com.bielevote.backend.reward_shop.Reward;
import com.bielevote.backend.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @NonNull
    @Enumerated(value = EnumType.STRING)
    private TransactionReason reason;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "reward_id")
    private Reward reward;
}
