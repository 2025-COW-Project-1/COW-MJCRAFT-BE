package com.example.cowmjucraft.domain.introduce.project.repository;

import java.util.List;

import com.example.cowmjucraft.domain.introduce.project.entity.ProjectImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectImageRepository extends JpaRepository<ProjectImage, Long> {
    List<ProjectImage> findAllByProjectIdOrderBySortOrderAscIdAsc(Long projectId);
    void deleteAllByProjectId(Long projectId);
}
