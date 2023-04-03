package com.dku.mentoring.register.model.dto.response;

import com.dku.mentoring.register.model.entity.Register;
import com.dku.mentoring.register.model.entity.RegisterStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseSingleRegisterDto {

    @Schema(description = "등록 글 ID", example = "1")
    private final Long id;

    @Schema(description = "제목", example = "제목")
    private final String title;

    @Schema(description = "본문", example = "본문")
    private final String body;

    @Schema(description = "작성자", example = "작성자")
    private final String userName;

    @Schema(description = "팀명", example = "팀명")
    private final String teamName;

    @Schema(description = "미션 설명", example = "미션 설명")
    private final String missionDescription;

    @Schema(description = "미션 카테고리", example = "미션 카테고리")
    private final String missionCategory;

    @Schema(description = "등록 상태", example = "PROGRESS")
    private final RegisterStatus status;

    @Schema(description = "등록 일시", example = "2023-01-01T00:00:00")
    private final LocalDateTime createAt;

    @Schema(description = "수정 일시", example = "2021-01-01T00:00:00")
    private final LocalDateTime lastModifiedAt;

    public ResponseSingleRegisterDto(Register register) {
        this.id = register.getId();
        this.title = register.getTitle();
        this.body = register.getBody();
        this.userName = register.getUser().getName();
        this.teamName = register.getUser().getTeam().getTeamName();
        this.missionDescription = register.getMission().getDescription();
        this.missionCategory = register.getMission().getCategory().name();
        this.status = register.getStatus();
        this.createAt = register.getCreatedAt();
        this.lastModifiedAt = register.getLastModifiedAt();
    }
}
