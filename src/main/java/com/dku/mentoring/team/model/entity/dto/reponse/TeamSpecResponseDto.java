package com.dku.mentoring.team.model.entity.dto.reponse;

import com.dku.mentoring.mission.model.entity.Mission;
import com.dku.mentoring.register.model.entity.Register;
import com.dku.mentoring.team.model.entity.Team;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TeamSpecResponseDto {

    @Schema(description = "팀 아이디", example = "1")
    private final Long id;

    @Schema(description = "멘토 이름", example = "멘토 이름")
    private final String userName_mentor;

    @Schema(description = "팀 이름", example = "팀 이름")
    private final String teamName;

    @Schema(description = "팀 점수", example = "100")
    private final int score;

    @Schema(description = "완료한 미션", example = "미션1")
    private List<String> completedMission = new ArrayList<>();

    public TeamSpecResponseDto(Team team, List<String> missions) {
        this.id = team.getId();
        this.userName_mentor = team.getUser().getName();
        this.teamName = team.getTeamName();
        this.score = team.getScore();
        this.completedMission = missions;
    }
}
