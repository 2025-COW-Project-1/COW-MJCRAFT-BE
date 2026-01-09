package com.example.cowmjucraft.domain.introduce.sns.repository;

import com.example.cowmjucraft.domain.introduce.sns.entity.SnsLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SnsLinkRepository extends JpaRepository<SnsLink, Long> {
    List<SnsLink> findByActiveTrueOrderBySortOrderAsc();
}
