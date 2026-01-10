package com.example.cowmjucraft.domain.introduce.intro.repository;

import com.example.cowmjucraft.domain.introduce.intro.entity.IntroduceContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IntroduceContentRepository extends JpaRepository<IntroduceContent, Long> {
    Optional<IntroduceContent> findTopByOrderByIdAsc();
}
