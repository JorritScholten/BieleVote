package com.bielevote.backend.user.rewardpoint;

import com.bielevote.backend.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Builder
@Entity
@Table(name = "REWARD_POINT_TRANSACTIONS")
public class RewardPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer amount;
    @Column(columnDefinition = "TIMESTAMP(0)")
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    @JsonBackReference
    private User user;
    @Enumerated(value = EnumType.STRING)
    private TransactionReason reason;
}
