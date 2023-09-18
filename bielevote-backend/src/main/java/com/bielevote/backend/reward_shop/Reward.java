package com.bielevote.backend.reward_shop;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

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
    private LocalDate datePlaced;
    private int cost;
}
