package com.dku.mentoring.mission.model.dto.response;

import com.dku.mentoring.mission.model.entity.MissionDifficulty;
import lombok.Getter;

@Getter
public class ResponseDifficultyListDto {
    private final String id;

    private final String name;
    public ResponseDifficultyListDto(MissionDifficulty difficulty) {
        this.id = difficulty.name();
        this.name = difficulty.getName();
    }
}
