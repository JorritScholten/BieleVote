package com.bielevote.backend.user;

import com.bielevote.backend.user.rewardpoint.RewardPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserRepository userRepository;
    private final RewardPointRepository rewardPointRepository;

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(@AuthenticationPrincipal User currentUser) {
        try {
            return ResponseEntity.ok(userRepository.findByUsername(currentUser.getUsername()).orElseThrow());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/balance")
    public ResponseEntity<String> getUserPointBalance(@AuthenticationPrincipal User currentUser) {
        try {
            var user = userRepository.findByUsername(currentUser.getUsername()).orElseThrow();
            var transactionList = rewardPointRepository.findByUser(user);
            var balance = transactionList.stream().flatMapToInt(t -> IntStream.of(t.getAmount())).sum();
            return ResponseEntity.ok("{\n  \"balance\": " + balance + "\n}\n");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
