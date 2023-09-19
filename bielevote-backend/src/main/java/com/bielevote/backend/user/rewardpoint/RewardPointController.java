package com.bielevote.backend.user.rewardpoint;

import com.bielevote.backend.user.User;
import com.bielevote.backend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/leaderboard")
public class RewardPointController {
    private final UserRepository userRepository;
    private final RewardPointRepository rewardPointRepository;

    @GetMapping
    public ResponseEntity<List<scoreCard>> getHighScores() {
        try {
            var merits = rewardPointRepository.findAll().stream().filter(t -> t.getAmount() > 0).toList();
            var users = merits.stream().map(RewardPoint::getUser).distinct().toList();
            TreeMap<Integer, User> leaderboard = new TreeMap<>();
            for (var key : users) {
                leaderboard.put(merits.stream().filter(t -> t.getUser().equals(key))
                        .flatMapToInt(t -> IntStream.of(t.getAmount())).sum(), key
                );
            }
            List<scoreCard> leaderboardList = new ArrayList<>();
            leaderboard.descendingMap()
                    .forEach((score, user) -> leaderboardList.add(new scoreCard(user.getUsername(), score)));
            return ResponseEntity.ok(leaderboardList);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    record scoreCard(String username, Integer score) {
    }
}
