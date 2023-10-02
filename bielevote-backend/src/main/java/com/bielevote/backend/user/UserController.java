package com.bielevote.backend.user;

import com.bielevote.backend.user.rewardpoint.TransactionRepository;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    @JsonView(UserViews.viewMe.class)
    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(@AuthenticationPrincipal User currentUser) {
        try {
            return ResponseEntity.ok(currentUser);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/balance")
    public ResponseEntity<Integer> getUserPointBalance(@AuthenticationPrincipal User currentUser) {
        try {
            var transactionList = transactionRepository.findByUser(currentUser);
            var balance = transactionList.stream().flatMapToInt(t -> IntStream.of(t.getAmount())).sum();
            return ResponseEntity.ok(balance);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @JsonView(UserViews.viewMe.class)
    @PatchMapping("/update/anonymous")
    public ResponseEntity<User> toggleAnonymous(@AuthenticationPrincipal User currentUser){
        try {
            currentUser.setAnonymousOnLeaderboard(!currentUser.getAnonymousOnLeaderboard());
            return new ResponseEntity<>(userRepository.save(currentUser), HttpStatus.OK);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping("/update/username")
    public ResponseEntity<User> patchUsername(@Validated @RequestBody String newUsername,
                                              @AuthenticationPrincipal User currentUser) {
        if (newUsername.isBlank()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            currentUser.setUsername(newUsername);
            return new ResponseEntity<>(userRepository.save(currentUser), HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
