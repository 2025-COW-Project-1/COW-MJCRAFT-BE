package com.example.cowmjucraft.domain.introduce.project.service;

import java.util.List;

import com.example.cowmjucraft.domain.introduce.project.dto.request.AdminProjectRequestDto;
import com.example.cowmjucraft.domain.introduce.project.dto.response.AdminProjectResponseDto;
import com.example.cowmjucraft.domain.introduce.project.dto.response.ClientProjectDetailResponseDto;
import com.example.cowmjucraft.domain.introduce.project.dto.response.ClientProjectResponseDto;
import com.example.cowmjucraft.domain.introduce.project.entity.Project;
import com.example.cowmjucraft.domain.introduce.project.entity.ProjectImage;
import com.example.cowmjucraft.domain.introduce.project.entity.ProjectStatus;
import com.example.cowmjucraft.domain.introduce.project.repository.ProjectImageRepository;
import com.example.cowmjucraft.domain.introduce.project.repository.ProjectRepository;
import com.example.cowmjucraft.domain.media.entity.Media;
import com.example.cowmjucraft.domain.media.entity.MediaStatus;
import com.example.cowmjucraft.domain.media.entity.MediaUsageType;
import com.example.cowmjucraft.domain.media.entity.MediaVisibility;
import com.example.cowmjucraft.domain.media.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectImageRepository projectImageRepository;
    private final MediaRepository mediaRepository;

    @Transactional
    public AdminProjectResponseDto create(AdminProjectRequestDto request) {
        validateThumbnailMediaId(request.thumbnailMediaId());
        validateImageMediaIds(request.imageMediaIds());

        Project project = new Project(
                request.title(),
                request.summary(),
                request.description(),
                request.basePrice(),
                request.salesLink(),
                request.sortOrder(),
                request.thumbnailMediaId()
        );
        projectRepository.save(project);
        saveProjectImages(project, request.imageMediaIds());
        return AdminProjectResponseDto.from(project);
    }

    @Transactional
    public AdminProjectResponseDto update(Long id, AdminProjectRequestDto request) {
        validateThumbnailMediaId(request.thumbnailMediaId());
        validateImageMediaIds(request.imageMediaIds());

        Project project = getProjectOrThrow(id);

        project.update(
                request.title(),
                request.summary(),
                request.description(),
                request.basePrice(),
                request.salesLink(),
                request.sortOrder(),
                request.thumbnailMediaId()
        );
        // TODO: 전체 교체 방식 대신 부분 업데이트로 개선해 불필요한 삭제/삽입을 줄일 수 있음.
        projectImageRepository.deleteAllByProjectId(project.getId());
        saveProjectImages(project, request.imageMediaIds());
        return AdminProjectResponseDto.from(project);
    }

    @Transactional
    public void publish(Long id) {
        Project project = getProjectOrThrow(id);

        if (project.getStatus() == ProjectStatus.ARCHIVED) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Archived project cannot be published");
        }

        if (project.getStatus() == ProjectStatus.PUBLISHED) {
            return;
        }

        project.publish();
    }

    @Transactional
    public void archive(Long id) {
        Project project = getProjectOrThrow(id);

        if (project.getStatus() == ProjectStatus.ARCHIVED) {
            return;
        }

        project.archive();
    }

    @Transactional(readOnly = true)
    public List<ClientProjectResponseDto> getPublishedProjects() {
        return projectRepository.findAllByStatusOrderBySortOrderAscIdAsc(ProjectStatus.PUBLISHED).stream()
                .map(ClientProjectResponseDto::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public ClientProjectDetailResponseDto getPublishedProject(Long id) {
        Project project = projectRepository.findByIdAndStatus(id, ProjectStatus.PUBLISHED)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
        List<Long> imageMediaIds = projectImageRepository.findAllByProjectIdOrderBySortOrderAscIdAsc(project.getId())
                .stream()
                .map(ProjectImage::getMediaId)
                .toList();
        return ClientProjectDetailResponseDto.from(project, imageMediaIds);
    }

    private Project getProjectOrThrow(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
    }

    private void saveProjectImages(Project project, List<Long> imageMediaIds) {
        if (imageMediaIds == null || imageMediaIds.isEmpty()) {
            return;
        }
        for (int i = 0; i < imageMediaIds.size(); i++) {
            Long mediaId = imageMediaIds.get(i);
            if (mediaId == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "imageMediaIds contains null");
            }
            projectImageRepository.save(new ProjectImage(project, mediaId, i));
        }
    }

    private void validateThumbnailMediaId(Long mediaId) {
        if (mediaId == null) {
            return;
        }
        validateMedia(mediaId);
    }

    private void validateImageMediaIds(List<Long> mediaIds) {
        if (mediaIds == null || mediaIds.isEmpty()) {
            return;
        }
        for (Long mediaId : mediaIds) {
            if (mediaId == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "imageMediaIds contains null");
            }
            validateMedia(mediaId);
        }
    }

    private void validateMedia(Long mediaId) {
        Media media = mediaRepository.findById(mediaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid mediaId"));
        if (media.getStatus() != MediaStatus.ACTIVE
                || media.getVisibility() != MediaVisibility.PUBLIC
                || media.getUsageType() != MediaUsageType.PROJECT) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid media state");
        }
    }
}
