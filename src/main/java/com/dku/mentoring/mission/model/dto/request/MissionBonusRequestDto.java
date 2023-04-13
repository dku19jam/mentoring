package com.dku.mentoring.mission.model.dto.request;


import com.dku.mentoring.mission.model.entity.Mission;
import com.dku.mentoring.mission.model.entity.MissionBonus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MissionBonusRequestDto {
    private String plusMission;
    private int plusPoint;

    public MissionBonusRequestDto(String plusMission, int plusPoint) {
        this.plusMission = plusMission;
        this.plusPoint = plusPoint;
    }

    public MissionBonus toEntity(Mission mission) {
        return MissionBonus.builder()
                .plusMission(plusMission)
                .plusPoint(plusPoint)
                .build();
    }
}
