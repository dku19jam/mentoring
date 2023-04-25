package com.dku.mentoring.mission.model.dto.response;

import com.dku.mentoring.mission.model.entity.Mission;
import com.dku.mentoring.mission.model.entity.MissionInfo;
import lombok.Getter;

@Getter
public class ResponseDifficultyListDto {
    private final String id;

    private final String name;
    public ResponseDifficultyListDto(MissionInfo info) {
        this.id = info.name();
        this.name = info.getName();
    }
}
