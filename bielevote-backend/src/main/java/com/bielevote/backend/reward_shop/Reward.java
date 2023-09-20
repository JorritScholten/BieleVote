package com.bielevote.backend.reward_shop;

import com.bielevote.backend.user.rewardpoint.RewardPoint;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Jacksonized
public class Reward {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private boolean isLimited;
    private int inventory;
    @Column(columnDefinition = "TIMESTAMP(0)")
    private LocalDateTime datePlaced;
    private int cost;
    @OneToOne
    @JoinColumn(name = "reward_point_transaction_id")
    private RewardPoint transaction;
}
