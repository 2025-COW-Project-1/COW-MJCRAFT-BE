package com.example.cowmjucraft.domain.introduce.project.repository;

import java.util.List;
import java.util.Optional;

import com.example.cowmjucraft.domain.introduce.project.entity.Project;
import com.example.cowmjucraft.domain.introduce.project.entity.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByStatusOrderBySortOrderAscIdAsc(ProjectStatus status);
    Optional<Project> findByIdAndStatus(Long id, ProjectStatus status);
}
