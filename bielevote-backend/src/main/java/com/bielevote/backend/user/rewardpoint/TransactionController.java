package com.bielevote.backend.user.rewardpoint;

import com.bielevote.backend.user.UserRepository;
import com.bielevote.backend.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/leaderboard")
public class TransactionController {
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    @GetMapping
    public ResponseEntity<List<scoreCard>> getHighScores(@RequestHeader(value = "timeRange",
            defaultValue = "ALL_TIME") String timeRange) {
        try {
            var range = switch (timeRange) {
                case "LAST_WEEK" -> LocalDateTime.now().minusWeeks(1);
                case "LAST_MONTH" -> LocalDateTime.now().minusMonths(1);
                case "LAST_YEAR" -> LocalDateTime.now().minusYears(1);
                default -> LocalDateTime.of(1900, 1, 1, 1, 1);
            };
            var merits = transactionRepository.findByAmountGreaterThanAndDateAfter(0, range);
            var users = merits.stream().map(Transaction::getUser).distinct()
                    .filter(user -> user.getRole().equals(UserRole.CITIZEN)).toList();
            List<scoreCardUnordered> unsortedLeaderboard = new ArrayList<>();
            for (var key : users) {
                unsortedLeaderboard.add(
                        new scoreCardUnordered(key.getAnonymousOnLeaderboard() ? "anonymous" : key.getLegalName(),
                                merits.stream().filter(t -> t.getUser().equals(key))
                                        .flatMapToInt(t -> IntStream.of(t.getAmount())).sum())
                );
            }
            unsortedLeaderboard.sort(null);
            List<scoreCard> leaderboard = new ArrayList<>();
            int rank = 1;
            for (var card : unsortedLeaderboard) {
                leaderboard.add(new scoreCard(card.name, card.score, rank));
                rank++;
            }
            return ResponseEntity.ok(leaderboard);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    public record scoreCard(String name, Integer score, Integer rank) {
    }

    record scoreCardUnordered(String name, Integer score) implements Comparable<scoreCardUnordered> {
        @Override
        public int compareTo(scoreCardUnordered o) {
            return o.score - score;
        }
    }
}
