package com.dku.mentoring.team.model.dto.reponse;

import com.dku.mentoring.register.model.entity.Register;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CompletedMissionResponseDto {

    private String missionName;

    private int score;

    public CompletedMissionResponseDto(Register register) {
        this.missionName = register.getMission().getName();
        this.score = register.getTotalScore();
    }
}
