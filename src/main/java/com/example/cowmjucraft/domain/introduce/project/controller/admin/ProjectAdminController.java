package com.example.cowmjucraft.domain.introduce.project.controller.admin;

import com.example.cowmjucraft.domain.introduce.project.dto.request.AdminProjectRequestDto;
import com.example.cowmjucraft.domain.introduce.project.dto.response.AdminProjectResponseDto;
import com.example.cowmjucraft.domain.introduce.project.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/projects")
public class ProjectAdminController implements ProjectAdminControllerDocs {

    private final ProjectService projectService;

    @Override
    @PostMapping
    public AdminProjectResponseDto create(@Valid @RequestBody AdminProjectRequestDto request) {
        return projectService.create(request);
    }

    @Override
    @PutMapping("/{id}")
    public AdminProjectResponseDto update(@PathVariable Long id, @Valid @RequestBody AdminProjectRequestDto request) {
        return projectService.update(id, request);
    }

    @Override
    @PostMapping("/{id}/publish")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void publish(@PathVariable Long id) {
        projectService.publish(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void archive(@PathVariable Long id) {
        projectService.archive(id);
    }
}
