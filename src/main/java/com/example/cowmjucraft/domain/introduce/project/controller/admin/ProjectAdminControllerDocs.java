package com.example.cowmjucraft.domain.introduce.project.controller.admin;

import com.example.cowmjucraft.domain.introduce.project.dto.request.AdminProjectRequestDto;
import com.example.cowmjucraft.domain.introduce.project.dto.response.AdminProjectResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Project (Admin)", description = "프로젝트 관리 API")
@SecurityRequirement(name = "bearerAuth")
public interface ProjectAdminControllerDocs {

    @Operation(summary = "프로젝트 생성", description = "프로젝트를 생성하며 초기 상태는 DRAFT입니다. 이미지 업로드는 Media presign → S3 PUT → activate 후 mediaId로 연결합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "생성 성공", content = @Content(schema = @Schema(implementation = AdminProjectResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "요청 값 오류"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "403", description = "권한 없음")
    })
    AdminProjectResponseDto create(
            @RequestBody(
                    required = true,
                    description = "프로젝트 생성 정보",
                    content = @Content(schema = @Schema(implementation = AdminProjectRequestDto.class))
            )
            @Valid
            AdminProjectRequestDto request
    );

    @Operation(summary = "프로젝트 수정", description = "프로젝트 내용을 전체 수정합니다. 이미지 업로드는 Media presign → S3 PUT → activate 후 mediaId로 연결합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "수정 성공", content = @Content(schema = @Schema(implementation = AdminProjectResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "요청 값 오류"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "403", description = "권한 없음"),
            @ApiResponse(responseCode = "404", description = "대상 없음")
    })
    AdminProjectResponseDto update(
            @PathVariable Long id,
            @RequestBody(
                    required = true,
                    description = "프로젝트 수정 정보",
                    content = @Content(schema = @Schema(implementation = AdminProjectRequestDto.class))
            )
            @Valid
            AdminProjectRequestDto request
    );

    @Operation(summary = "프로젝트 공개", description = "프로젝트 상태를 PUBLISHED로 변경하고 publishedAt을 설정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "공개 완료"),
            @ApiResponse(responseCode = "400", description = "요청 값 오류"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "403", description = "권한 없음"),
            @ApiResponse(responseCode = "404", description = "대상 없음")
    })
    void publish(@PathVariable Long id);

    @Operation(summary = "프로젝트 보관", description = "프로젝트 상태를 ARCHIVED로 변경합니다. 하드 삭제는 하지 않습니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "보관 완료"),
            @ApiResponse(responseCode = "400", description = "요청 값 오류"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "403", description = "권한 없음"),
            @ApiResponse(responseCode = "404", description = "대상 없음")
    })
    void archive(@PathVariable Long id);
}
