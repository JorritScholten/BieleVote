package com.bielevote.backend.user.rewardpoint;

import com.bielevote.backend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/leaderboard")
public class RewardPointController {
    private final UserRepository userRepository;
    private final RewardPointRepository rewardPointRepository;

    @GetMapping
    public ResponseEntity<Map<String, Integer>> getHighScore() {
        try {
            var merits = rewardPointRepository.findAll().stream().dropWhile(t -> t.getAmount() <= 0).toList();
            var users = merits.stream().map(RewardPoint::getUser).distinct().toList();
            Map<String, Integer> leaderboard = new HashMap<>();
            for (var key : users) {
                leaderboard.put(key.getUsername(), merits.stream().filter(t -> t.getUser().equals(key))
                        .flatMapToInt(t -> IntStream.of(t.getAmount())).sum()
                );
            }
            return ResponseEntity.ok(leaderboard);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
