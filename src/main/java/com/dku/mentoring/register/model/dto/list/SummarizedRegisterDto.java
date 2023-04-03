package com.dku.mentoring.register.model.dto.list;

import com.dku.mentoring.register.model.entity.Register;
import com.dku.mentoring.register.model.entity.RegisterStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;


@Getter
public class SummarizedRegisterDto {

    @Schema(description = "등록 아이디", example = "1")
    private final Long id;

    @Schema(description = "제목", example = "등록 제목")
    private final String title;

    @Schema(description = "본문", example = "등록 본문")
    private final String body;

    @Schema(description = "등록자", example = "익명")
    private final String userName;

    @Schema(description = "팀명", example = "팀명")
    private final String teamName;

    @Schema(description = "미션 설명", example = "미션 설명")
    private final String missionDescription;

    @Schema(description = "등록 상태", example = "PROGRESS")
    private final RegisterStatus status;

    @Schema(description = "등록 날짜", example = "2021-01-01")
    private final String date;


    public SummarizedRegisterDto(Register register) {
        this.id = register.getId();
        this.title = register.getTitle();
        this.body = register.getBody();
        this.userName = register.getUser().getName();
        this.teamName = register.getUser().getTeam().getTeamName();
        this.missionDescription = register.getMission().getDescription();
        this.status = register.getStatus();
        this.date = register.getLastModifiedAt().toString();
    }
}
