package com.bielevote.backend.user.rewardpoint;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RewardPointRepository extends JpaRepository<RewardPoint,Long> {
    List<RewardPoint> findByAuthor(@NonNull Long author_id);
}
