package com.dku.mentoring.team.model.dto.reponse;

import com.dku.mentoring.register.model.entity.Register;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CompletedMissionResponseDto {

    private Long id;

    private String missionName;

    private int totalScore;

    private String missionInfo;

    private String missionCategory;

    private LocalDateTime lastModifiedAt;

    public CompletedMissionResponseDto(Register register) {
        this.id = register.getId();
        this.missionName = register.getMission().getName();
        this.totalScore = register.getTotalScore();
        this.missionInfo = register.getMission().getInfo().getName();
        this.missionCategory = register.getMission().getCategory().getName();
        this.lastModifiedAt = register.getLastModifiedAt();
    }
}
