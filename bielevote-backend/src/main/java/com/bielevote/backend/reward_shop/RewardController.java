package com.bielevote.backend.reward_shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("api/v1/rewards")
public class RewardController {

    @Autowired
    RewardRepository rewardRepository;

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getAllRewards(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam() InputStreamResource jpg
    ) {
        try {
            List<Reward> rewards;
            PageRequest paging = PageRequest.of(page, size, Sort.by("datePlaced").descending());

            Page<Reward> pageRewards = rewardRepository.findAll(paging);

            rewards = pageRewards.getContent();

            InputStream in = getClass().getResourceAsStream(String.valueOf(jpg));

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("rewards", rewards);
            responseBody.put("currentPage", pageRewards.getNumber());
            responseBody.put("totalItems", pageRewards.getTotalElements());
            responseBody.put("totalPages", pageRewards.getTotalPages());
            responseBody.put("image", in);

            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
