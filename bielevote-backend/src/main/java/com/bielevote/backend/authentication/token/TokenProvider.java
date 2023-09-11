package com.bielevote.backend.authentication.token;

import com.bielevote.backend.authentication.CustomUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TokenProvider {
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "order-api";
    public static final String TOKEN_AUDIENCE = "order-app";
    @Value("${app.jwt.secret}")
    private String jwtSecret;
    @Value("${app.jwt.expiration.minutes}")
    private Long jwtExpirationMinutes;

    public String generate(Authentication authentication) {
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        byte[] signingKey = jwtSecret.getBytes();
        return Jwts.builder()
                .setHeaderParam("typ", TOKEN_TYPE)
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(jwtExpirationMinutes).toInstant()))
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setId(UUID.randomUUID().toString())
                .setIssuer(TOKEN_ISSUER)
                .setAudience(TOKEN_AUDIENCE)
                .setSubject(user.getUsername())
                .claim("rol", roles)
                .claim("id", user.getId())
                .claim("username", user.getUsername())
                .compact();
    }

    public Optional<Jws<Claims>> validateTokenAndGetJws(String token) {
        try {
            byte[] signingKey = jwtSecret.getBytes();
            Jws<Claims> jws = Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token);
            return Optional.of(jws);
        } catch (ExpiredJwtException e) {
            System.err.println("Request to parse expired JWT : " + token + " failed : " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.err.println("Request to parse unsupported JWT : " + token + " failed : " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.err.println("Request to parse invalid JWT : " + token + " failed : " + e.getMessage());
        } catch (SecurityException e) {
            System.err.println("Request to parse JWT with invalid signature : " + token + " failed : " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Request to parse empty or null JWT : " + token + " failed : " + e.getMessage());
        }
        return Optional.empty();
    }
}
