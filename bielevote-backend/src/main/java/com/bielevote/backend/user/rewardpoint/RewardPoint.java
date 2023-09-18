package com.bielevote.backend.user.rewardpoint;

import com.bielevote.backend.user.User;
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
@Builder
@Entity(name = "REWARD_POINT_TRANSACTIONS")
public class RewardPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer amount;
    @Column(columnDefinition = "TIMESTAMP(0)")
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonManagedReference
    private User author;
    @Enumerated(value = EnumType.STRING)
    private TransactionReason reason;
}
