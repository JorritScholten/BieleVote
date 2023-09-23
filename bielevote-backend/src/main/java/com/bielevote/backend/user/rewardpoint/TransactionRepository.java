package com.bielevote.backend.user.rewardpoint;

import com.bielevote.backend.user.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findByUser(@NonNull User user);
    List<Transaction> findByAmountGreaterThanAndDateAfter(@NonNull Integer amount, @NonNull LocalDateTime date);
}
