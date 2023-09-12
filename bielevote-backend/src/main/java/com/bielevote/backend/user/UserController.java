package com.bielevote.backend.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserRepository userRepository;

    @PreAuthorize("hasAuthority('read')")
    @GetMapping("/me")
    public ResponseEntity<String> getCurrentUser(@AuthenticationPrincipal User currentUser) {
        try {
            var user = userRepository.findByUsername(currentUser.getUsername()).orElseThrow();
            return ResponseEntity.ok(JsonMapper.builder()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(user));
        } catch (JsonProcessingException e) {
            return ResponseEntity.internalServerError().body("Couldn't perform serialization.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
