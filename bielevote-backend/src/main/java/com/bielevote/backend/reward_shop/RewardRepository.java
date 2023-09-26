package com.bielevote.backend.reward_shop;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RewardRepository extends JpaRepository<Reward, Long> {
    Page<Reward> findByIsAvailable(@NonNull Boolean isAvailable, Pageable pageable);

    Optional<Reward> findByIdAndIsAvailable(@NonNull Long id, @NonNull Boolean isAvailable);
}
