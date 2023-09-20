package com.bielevote.backend.reward_shop;

import com.bielevote.backend.user.User;
import com.bielevote.backend.user.UserRepository;
import com.bielevote.backend.user.UserService;
import com.bielevote.backend.user.rewardpoint.RewardPoint;
import com.bielevote.backend.user.rewardpoint.RewardPointRepository;
import com.bielevote.backend.user.rewardpoint.TransactionReason;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/rewards")
public class RewardController {
    private final RewardRepository rewardRepository;
    private final UserRepository userRepository;
    private final RewardPointRepository rewardPointRepository;
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
    public ResponseEntity<List<RewardPoint>> getRedeemedRewards(@AuthenticationPrincipal User currentUser) {
        try {
            var user = userRepository.findByUsername(currentUser.getUsername()).orElseThrow();
            var transactionList = rewardPointRepository.findByUser(user);
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
    public ResponseEntity<RewardPoint> postRedeemedReward(@Validated @RequestBody RewardPoint rewardRedeemed,
                                                          @AuthenticationPrincipal User currentUser) {
        try {
//            var rewardBought = new Reward();
            var user = userRepository.findByUsername(currentUser.getUsername()).orElseThrow();
            rewardRedeemed.setUser(user);
            rewardRedeemed.setDate(LocalDateTime.now());
//            rewardRedeemed.setReason(TransactionReason.REDEEMED_REWARD);
//            rewardRedeemed.setReward(rewardBought);
//            rewardRedeemed.setAmount(rewardRedeemed.getRewardsAmount() * -rewardBought.getCost());
            return ResponseEntity.ok(rewardPointRepository.save(rewardRedeemed));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
