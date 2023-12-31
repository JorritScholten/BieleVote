package com.bielevote.backend.reward_shop;

import com.bielevote.backend.user.rewardpoint.Transaction;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Jacksonized
@Table(name = "REWARDS")
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REWARDS_ID_SEQ")
    @SequenceGenerator(name = "REWARDS_ID_SEQ", sequenceName = "REWARDS_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;

    private String name;
    private String description;
    private Boolean isLimited;
    private Integer inventory;
    @Column(columnDefinition = "TIMESTAMP(0)")
    private LocalDateTime datePlaced;
    private Integer cost;
    private Boolean isAvailable;

    @JsonBackReference
    @OneToMany(mappedBy = "reward")
    private List<Transaction> transactions;
}
