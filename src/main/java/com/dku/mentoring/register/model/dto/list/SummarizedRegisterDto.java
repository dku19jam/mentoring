package com.dku.mentoring.register.model.dto.list;

import com.dku.mentoring.register.model.entity.Register;
import com.dku.mentoring.register.model.entity.RegisterFile;
import com.dku.mentoring.register.model.entity.RegisterStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Getter
public class SummarizedRegisterDto {

    @Schema(description = "등록 아이디", example = "1")
    private final Long id;

    @Schema(description = "제목", example = "등록 제목")
    private final String title;

    @Schema(description = "본문", example = "본문")
    private final String body;

    @Schema(description = "팀 아이디", example = "1")
    private final Long teamId;

    @Schema(description = "팀명", example = "팀명")
    private final String teamName;

    @Schema(description = "미션 이름", example = "미션 이름")
    private final String missionName;

    @Schema(description = "등록 상태", example = "PROGRESS")
    private final RegisterStatus status;

    @Schema(description = "총 점수", example = "100")
    private final int totalScore;

    @Schema(description = "등록 날짜", example = "2021-01-01")
    private final LocalDateTime date;

    private final List<Long> registerFiles;


    public SummarizedRegisterDto(Register register) {
        this.id = register.getId();
        this.title = register.getTitle();
        this.body = register.getBody();
        this.teamId = register.getUser().getTeam().getId();
        this.teamName = register.getUser().getTeam().getTeamName();
        this.missionName = register.getMission().getName();
        this.status = register.getStatus();
        this.totalScore = register.getTotalScore();
        this.date = register.getLastModifiedAt();
        this.registerFiles = register.getFiles().stream()
                .map(RegisterFile::getId).collect(Collectors.toList());
    }
}
