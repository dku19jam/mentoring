package com.dku.mentoring.team.model.dto.reponse;

import com.dku.mentoring.team.model.entity.Team;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TeamSpecResponseDto {

    @Schema(description = "팀 아이디", example = "1")
    private final Long id;

    @Schema(description = "멘토 이름", example = "멘토 이름")
    private final String userName_mentor;

    @Schema(description = "팀 이름", example = "팀 이름")
    private final String teamName;

    @Schema(description = "멘티", example = "멘티")
    private final String mentee;

    @Schema(description = "팀 점수", example = "100")
    private final int score;

    @Schema(description = "완료한 미션", example = "미션1")
    private List<CompletedMissionResponseDto> completedMission = new ArrayList<>();

    public TeamSpecResponseDto(Team team, List<CompletedMissionResponseDto> missions) {
        this.id = team.getId();
        this.userName_mentor = team.getUser().getName();
        this.teamName = team.getTeamName();
        this.mentee = team.getMentee();
        this.score = team.getScore();
        this.completedMission = missions;
    }
}
