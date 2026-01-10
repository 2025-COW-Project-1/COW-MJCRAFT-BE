package com.example.cowmjucraft.domain.introduce.project.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(description = "프로젝트 생성/수정 요청 DTO")
public record AdminProjectRequestDto(
        @NotBlank
        @Schema(description = "프로젝트 제목", example = "명지공방 굿즈 프로젝트")
        String title,

        @Schema(description = "프로젝트 요약", example = "학생 제작 굿즈 소개")
        String summary,

        @NotBlank
        @Schema(description = "프로젝트 상세 설명", example = "프로젝트 상세 내용입니다.")
        String description,

        @NotNull
        @PositiveOrZero
        @Schema(description = "기준 가격", example = "15000")
        Long basePrice,

        @Schema(description = "판매 링크", example = "https://example.com/shop")
        String salesLink,

        @PositiveOrZero
        @Schema(description = "정렬 순서", example = "1")
        int sortOrder,

        @Schema(description = "대표 이미지 mediaId (Media presign → S3 업로드 → activate 후 연결)", example = "10")
        Long thumbnailMediaId,

        @Schema(description = "상세 이미지 mediaId 목록 (순서대로 sortOrder로 저장)", example = "[11, 12, 13]")
        List<Long> imageMediaIds
) {
}
