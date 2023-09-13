package com.bielevote.backend.authentication;

import com.bielevote.backend.authentication.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @GetMapping("/test")
    public ResponseEntity<String> test_endpoint() {
        return ResponseEntity.ok("Hello world");
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password)
        );
        String token = tokenProvider.generate(auth);
        return new AuthResponse(token);
    }

    public record LoginRequest(String username, String password) {
    }

    public record AuthResponse(String accessToken) {
    }
}
