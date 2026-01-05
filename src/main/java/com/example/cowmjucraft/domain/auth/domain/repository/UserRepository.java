package com.example.cowmjucraft.domain.auth.domain.repository;

import com.example.cowmjucraft.domain.auth.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserId(String userId);
    Optional<User> findByUserId(String userId);
}
