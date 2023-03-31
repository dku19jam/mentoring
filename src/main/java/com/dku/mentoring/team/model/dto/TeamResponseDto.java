package com.dku.mentoring.team.model.dto;

import lombok.Getter;

@Getter
public class TeamResponseDto {
    private Long id;
    private String teamName;
    private int score;

    public TeamResponseDto(Long id, String teamName, int score) {
        this.id = id;
        this.teamName = teamName;
        this.score = score;
    }
}
