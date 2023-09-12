package com.bielevote.backend.user;

import com.bielevote.backend.authentication.Authority;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum UserRole {
    ADMINISTRATOR(Set.of(
            Authority.READ,
            Authority.WRITE,
            Authority.DELETE,
            Authority.UPDATE
    )),
    MUNICIPAL(Set.of(
            Authority.READ,
            Authority.WRITE,
            Authority.DELETE,
            Authority.UPDATE
    )),
    CITIZEN(Set.of(
            Authority.READ,
            Authority.WRITE,
            Authority.DELETE,
            Authority.UPDATE
    ));

    @Getter
    private final Set<Authority> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
