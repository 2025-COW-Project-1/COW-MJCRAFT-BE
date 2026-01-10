package com.example.cowmjucraft.domain.introduce.project.controller.client;

import com.example.cowmjucraft.domain.introduce.project.dto.response.ClientProjectDetailResponseDto;
import com.example.cowmjucraft.domain.introduce.project.dto.response.ClientProjectResponseDto;
import com.example.cowmjucraft.domain.introduce.project.service.ProjectService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/projects")
public class ProjectController implements ProjectControllerDocs {

    private final ProjectService projectService;

    @Override
    @GetMapping
    public List<ClientProjectResponseDto> getProjects() {
        return projectService.getPublishedProjects();
    }

    @Override
    @GetMapping("/{id}")
    public ClientProjectDetailResponseDto getProject(@PathVariable Long id) {
        return projectService.getPublishedProject(id);
    }
}
