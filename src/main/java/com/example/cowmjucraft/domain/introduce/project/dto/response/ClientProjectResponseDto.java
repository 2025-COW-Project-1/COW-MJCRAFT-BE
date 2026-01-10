package com.example.cowmjucraft.domain.introduce.project.dto.response;

import java.time.LocalDateTime;

import com.example.cowmjucraft.domain.introduce.project.entity.Project;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "프로젝트 공개 응답 DTO")
public record ClientProjectResponseDto(
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
        @Schema(description = "대표 이미지 mediaId", example = "10")
        Long thumbnailMediaId,
        @Schema(description = "판매 링크", example = "https://example.com/shop")
        String salesLink,
        @Schema(description = "정렬 순서", example = "1")
        int sortOrder,
        @Schema(description = "공개 시각", example = "2024-01-03T12:00:00")
        LocalDateTime publishedAt
) {
    public static ClientProjectResponseDto from(Project project) {
        return new ClientProjectResponseDto(
                project.getId(),
                project.getTitle(),
                project.getSummary(),
                project.getDescription(),
                project.getBasePrice(),
                project.getThumbnailMediaId(),
                project.getSalesLink(),
                project.getSortOrder(),
                project.getPublishedAt()
        );
    }
}
