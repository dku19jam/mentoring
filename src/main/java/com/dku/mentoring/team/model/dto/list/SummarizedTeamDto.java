package com.dku.mentoring.team.model.dto.list;

import com.dku.mentoring.team.model.entity.Team;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SummarizedTeamDto {

    private final Long id;

    private final String teamName;

    private final String userName_mentor;

    private final String mentee;

    private final int score;

    @Builder
    public SummarizedTeamDto(Team team) {
        this.id = team.getId();
        this.teamName = team.getTeamName();
        this.userName_mentor = team.getUser().getName();
        this.mentee = team.getMentee();
        this.score = team.getScore();
    }
}
