package com.example.cowmjucraft.domain.introduce.project.controller.client;

import com.example.cowmjucraft.domain.introduce.project.dto.response.ClientProjectDetailResponseDto;
import com.example.cowmjucraft.domain.introduce.project.dto.response.ClientProjectResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Project (Public)", description = "프로젝트 공개 조회 API")
public interface ProjectControllerDocs {

    @Operation(summary = "프로젝트 목록 조회", description = "PUBLISHED 상태의 프로젝트만 정렬 순서 기준으로 조회합니다. 이미지는 mediaId만 반환합니다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "조회 성공",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ClientProjectResponseDto.class)))
            )
    })
    List<ClientProjectResponseDto> getProjects();

    @Operation(
            summary = "프로젝트 상세 조회",
            description = "PUBLISHED 상태의 프로젝트만 조회할 수 있습니다. 이미지 업로드는 Media presign → S3 PUT → activate 후 mediaId로 연결합니다."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = ClientProjectDetailResponseDto.class))
            ),
            @ApiResponse(responseCode = "404", description = "대상 없음")
    })
    ClientProjectDetailResponseDto getProject(@PathVariable Long id);
}
