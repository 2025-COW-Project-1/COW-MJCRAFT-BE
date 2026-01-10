package com.example.cowmjucraft.domain.introduce.project.dto.response;

import java.time.LocalDateTime;

import com.example.cowmjucraft.domain.introduce.project.entity.Project;
import com.example.cowmjucraft.domain.introduce.project.entity.ProjectStatus;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "프로젝트 관리자 응답 DTO")
public record AdminProjectResponseDto(
        @Schema(description = "프로젝트 ID", example = "1")
        Long id,
        @Schema(description = "프로젝트 제목", example = "명지공방 굿즈 프로젝트")
        String title,
        @Schema(description = "프로젝트 요약", example = "학생 제작 굿즈 소개")
        String summary,
        @Schema(description = "프로젝트 상세 설명", example = "프로젝트 상세 내용입니다.")
        String description,
        @Schema(description = "기준 가격", example = "15000")
        Long basePrice,
        @Schema(description = "프로젝트 상태", example = "DRAFT")
        ProjectStatus status,
        @Schema(description = "판매 링크", example = "https://example.com/shop")
        String salesLink,
        @Schema(description = "정렬 순서", example = "1")
        int sortOrder,
        @Schema(description = "생성 시각", example = "2024-01-01T12:00:00")
        LocalDateTime createdAt,
        @Schema(description = "수정 시각", example = "2024-01-02T12:00:00")
        LocalDateTime updatedAt,
        @Schema(description = "공개 시각", example = "2024-01-03T12:00:00")
        LocalDateTime publishedAt
) {
    public static AdminProjectResponseDto from(Project project) {
        return new AdminProjectResponseDto(
                project.getId(),
                project.getTitle(),
                project.getSummary(),
                project.getDescription(),
                project.getBasePrice(),
                project.getStatus(),
                project.getSalesLink(),
                project.getSortOrder(),
                project.getCreatedAt(),
                project.getUpdatedAt(),
                project.getPublishedAt()
        );
    }
}
