package com.bielevote.backend.user;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(@NonNull String username);

    Optional<User> findByLegalNameAndPhone(@NonNull String legalName, @NonNull String phone);
}
