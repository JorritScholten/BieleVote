package com.bielevote.backend.reward_shop;

import com.bielevote.backend.project.Project;
import com.bielevote.backend.project.ProjectStatus;
import com.bielevote.backend.user.User;
import com.bielevote.backend.user.UserRepository;
import com.bielevote.backend.user.UserRole;
import com.bielevote.backend.user.rewardpoint.Transaction;
import com.bielevote.backend.user.rewardpoint.TransactionReason;
import com.bielevote.backend.user.rewardpoint.TransactionRepository;
import jakarta.persistence.Column;
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

    @GetMapping("/shop")
    public ResponseEntity<Map<String, Object>> getAllRewards(@AuthenticationPrincipal User currentUser,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size
    ) {
        try {
            List<Reward> rewards;
            Page<Reward> pageRewards;
            PageRequest paging = PageRequest.of(page, size, Sort.by("datePlaced").descending());
            if (currentUser != null && currentUser.getRole() == UserRole.ADMINISTRATOR) {
                pageRewards = rewardRepository.findAll(paging);
            } else {
                pageRewards = rewardRepository.findByIsAvailable(true, paging);
            }

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
    public ResponseEntity<Reward> getRewardById(@AuthenticationPrincipal User currentUser,
                                                @PathVariable("id") long id) {
        try {
            if (currentUser != null && currentUser.getRole() == UserRole.ADMINISTRATOR)
                return new ResponseEntity<>(rewardRepository.findById(id).orElseThrow(), HttpStatus.OK);
            else {
                return new ResponseEntity<>(rewardRepository.findByIdAndIsAvailable(id, true).orElseThrow(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/redeemed")
    public ResponseEntity<Map<String, Object>> getRedeemedRewards(@AuthenticationPrincipal User currentUser,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        try {
            List<Transaction> transactions;
            PageRequest paging = PageRequest.of(page, size, Sort.by("date").descending());
            var user = userRepository.findByUsername(currentUser.getUsername()).orElseThrow();
            Page<Transaction> pageTransaction = transactionRepository.findByUserAndReason(user, TransactionReason.REDEEMED_REWARD, paging);

            transactions = pageTransaction.getContent();

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("transactions", transactions);
            responseBody.put("currentPage", pageTransaction.getNumber());
            responseBody.put("totalItems", pageTransaction.getTotalElements());
            responseBody.put("totalPages", pageTransaction.getTotalPages());

            return ResponseEntity.ok(responseBody);
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

    @PostMapping("/shop")
    public ResponseEntity<Void> postReward(@Validated @RequestBody CreateRewardDto createRewardDto) {
        try {
            var date = LocalDateTime.now();
            var reward = new Reward();
            reward.setName(createRewardDto.name);
            reward.setDescription(createRewardDto.description);
            reward.setIsLimited(createRewardDto.isLimited);
            reward.setInventory(createRewardDto.inventory);
            reward.setDatePlaced(date);
            reward.setCost(createRewardDto.cost);
            reward.setIsAvailable(createRewardDto.isAvailable);

            rewardRepository.save(reward);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping("/shop/inventory/{id}")
    public ResponseEntity<Reward> changeRewardInventory(@PathVariable("id") long id,
                                                        @RequestHeader(value = "newInventory") String inventory) {
        try {
            var newInventory = Integer.parseInt(inventory);
            var reward = rewardRepository.findById(id).orElseThrow();
            if (newInventory < 0) throw new IllegalArgumentException();
            reward.setInventory(newInventory);
            return ResponseEntity.ok(rewardRepository.save(reward));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/shop/availability/{id}")
    public ResponseEntity<Reward> changeRewardAvailability(@PathVariable("id") long id,
                                                           @RequestHeader(value = "isAvailable") String isAvailable) {
        try {

            var newAvailability = String.valueOf(isAvailable);
            if (!newAvailability.equals(Boolean.toString(true)) || !newAvailability.equals(Boolean.toString(false))) {
                throw new IllegalArgumentException();
            }
            var reward = rewardRepository.findById(id).orElseThrow();
            reward.setIsAvailable(Boolean.valueOf(newAvailability));
            return ResponseEntity.ok(rewardRepository.save(reward));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    record RewardPurchasedDto(int rewardsAmount, Long rewardId) {
    }

    record CreateRewardDto(String name, String description, Boolean isLimited, Integer inventory, Integer cost,
                           Boolean isAvailable) {
    }
}
