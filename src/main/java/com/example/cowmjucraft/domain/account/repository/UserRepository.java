package com.example.cowmjucraft.domain.account.repository;

import com.example.cowmjucraft.domain.account.entity.User;
import com.example.cowmjucraft.domain.account.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserId(String userId);
    boolean existsByRole(Role role);
    Optional<User> findByUserId(String userId);
}
