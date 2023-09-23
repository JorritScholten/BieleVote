package com.bielevote.backend.user.rewardpoint;

import com.bielevote.backend.user.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RewardPointRepository extends JpaRepository<RewardPoint,Long> {
    List<RewardPoint> findByUser(@NonNull User user);
    List<RewardPoint> findByAmountGreaterThanAndDateAfter(@NonNull Integer amount, @NonNull LocalDateTime date);
}
