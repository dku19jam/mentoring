package com.dku.mentoring.mission.model.dto.response;

import com.dku.mentoring.mission.model.entity.MissionBonus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class MissionBonusResponseDto {
    private String plusMission;
    private int plusPoint;
    public MissionBonusResponseDto(String plusMission, int plusPoint) {
        this.plusMission = plusMission;
        this.plusPoint = plusPoint;
    }

    public static List<MissionBonusResponseDto> listOf(List<MissionBonus> bonusList) {
        return bonusList.stream()
                .map(bonus -> new MissionBonusResponseDto(bonus.getPlusMission(), bonus.getPlusPoint()))
                .collect(Collectors.toList());
    }
}

