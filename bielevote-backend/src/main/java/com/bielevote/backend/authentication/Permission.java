package com.bielevote.backend.authentication;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ANONYMOUS_READ(""),
    CITIZEN_READ("user:read");

    @Getter
    private final String permission;
}
