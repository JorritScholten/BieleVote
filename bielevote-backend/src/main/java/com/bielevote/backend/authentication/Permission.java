package com.bielevote.backend.authentication;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ANONYMOUS_READ(""),
    CITIZEN_READ("citizen:read"),
    CITIZEN_WRITE("citizen:write"),
    CITIZEN_UPDATE("citizen:update"),
    CITIZEN_DELETE("citizen:delete");

    @Getter
    private final String permission;
}
