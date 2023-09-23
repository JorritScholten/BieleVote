package com.bielevote.backend.reward_shop;

import com.bielevote.backend.user.User;
import com.bielevote.backend.user.UserRepository;
import com.bielevote.backend.user.UserService;
import com.bielevote.backend.user.rewardpoint.Transaction;
import com.bielevote.backend.user.rewardpoint.TransactionRepository;
import com.bielevote.backend.user.rewardpoint.TransactionReason;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.IntStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/rewards")
public class RewardController {
    private final RewardRepository rewardRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final UserService userService;

    @GetMapping("/shop")
    public ResponseEntity<Map<String, Object>> getAllRewards(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            List<Reward> rewards;
            PageRequest paging = PageRequest.of(page, size, Sort.by("datePlaced").descending());

            Page<Reward> pageRewards = rewardRepository.findAll(paging);

            rewards = pageRewards.getContent();

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("rewards", rewards);
            responseBody.put("currentPage", pageRewards.getNumber());
            responseBody.put("totalItems", pageRewards.getTotalElements());
            responseBody.put("totalPages", pageRewards.getTotalPages());

            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/shop/{id}")
    public ResponseEntity<Reward> getRewardById(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(rewardRepository.findById(id).orElseThrow(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/redeemed")
    public ResponseEntity<List<Transaction>> getRedeemedRewards(@AuthenticationPrincipal User currentUser) {
        try {
            var user = userRepository.findByUsername(currentUser.getUsername()).orElseThrow();
            var transactionList = transactionRepository.findByUser(user);
            var redeemedRewards = transactionList.stream().filter(rewardPoint -> rewardPoint.getReason() == TransactionReason.REDEEMED_REWARD).toList();
            return ResponseEntity.ok(redeemedRewards);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/redeemed")
    public ResponseEntity<Void> postRedeemedReward(@Validated @RequestBody RewardPurchasedDto rewardPurchasedDto,
                                                   @AuthenticationPrincipal User currentUser) {
        try {
            var user = userRepository.findByUsername(currentUser.getUsername()).orElseThrow();
            var reward = rewardRepository.findById(rewardPurchasedDto.rewardId).orElseThrow();
            var transactionList = transactionRepository.findByUser(user);
            var balance = transactionList.stream().flatMapToInt(t -> IntStream.of(t.getAmount())).sum();
            if (balance < rewardPurchasedDto.rewardsAmount * reward.getCost()) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            var date = LocalDateTime.now();
            List<Transaction> rewardsBought = new ArrayList<>();
            for (int i = 0; i < rewardPurchasedDto.rewardsAmount; i++) {
                rewardsBought.add(Transaction.builder()
                        .reward(reward)
                        .date(date)
                        .user(user)
                        .reason(TransactionReason.REDEEMED_REWARD)
                        .amount(-reward.getCost())
                        .build());
            }
            transactionRepository.saveAll(rewardsBought);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    record RewardPurchasedDto(int rewardsAmount, Long rewardId) {
    }
}
