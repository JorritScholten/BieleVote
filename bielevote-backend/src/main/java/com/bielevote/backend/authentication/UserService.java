package com.bielevote.backend.authentication;

import com.bielevote.backend.user.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getByUsername(String username);
}
