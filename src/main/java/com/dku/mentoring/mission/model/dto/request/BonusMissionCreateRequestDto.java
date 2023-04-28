package com.dku.mentoring.mission.model.dto.request;

import com.dku.mentoring.mission.model.entity.Mission;
import com.dku.mentoring.mission.model.entity.MissionBonus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BonusMissionCreateRequestDto {

    private Long missionId;

    private String bonusName;

    private int bonusPoint;

    public BonusMissionCreateRequestDto(Long missionId, String bonusName, int bonusPoint) {
        this.missionId = missionId;
        this.bonusName = bonusName;
        this.bonusPoint = bonusPoint;
    }

    public MissionBonus toEntity() {
        return MissionBonus.builder()
                .plusMission(bonusName)
                .plusPoint(bonusPoint)
                .build();
    }
}
