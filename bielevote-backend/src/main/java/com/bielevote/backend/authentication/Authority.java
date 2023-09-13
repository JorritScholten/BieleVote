package com.bielevote.backend.authentication;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Authority {
    READ("read"),
    WRITE("write"),
    UPDATE("update"),
    DELETE("delete");

    @Getter
    private final String value;
}
