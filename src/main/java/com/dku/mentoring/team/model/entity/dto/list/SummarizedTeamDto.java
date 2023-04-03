package com.dku.mentoring.team.model.entity.dto.list;

import com.dku.mentoring.team.model.entity.Team;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SummarizedTeamDto {

    private final Long id;

    private final String teamName;

    private final String userName_mentor;

    private final int score;

    @Builder
    public SummarizedTeamDto(Team team) {
        this.id = team.getId();
        this.teamName = team.getTeamName();
        this.userName_mentor = team.getUser().getName();
        this.score = team.getScore();
    }
}
