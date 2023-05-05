package com.dku.mentoring.team.model.dto.reponse;

import com.dku.mentoring.register.model.entity.Register;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CompletedMissionResponseDto {

    private Long registerId;

    private String missionName;

    private String missionDifficulty;

    private String missionCategory;

    private int score;

    private LocalDateTime lastModifiedAt;

    public CompletedMissionResponseDto(Register register) {
        this.registerId = register.getId();
        this.missionName = register.getMission().getName();
        this.missionDifficulty = register.getMission().getDifficulty().getName();
        this.missionCategory = register.getMission().getCategory().getName();
        this.score = register.getTotalScore();
        this.lastModifiedAt = register.getLastModifiedAt();
    }
}
