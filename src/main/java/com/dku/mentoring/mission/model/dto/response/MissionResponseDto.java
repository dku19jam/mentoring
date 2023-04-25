package com.dku.mentoring.mission.model.dto.response;

import com.dku.mentoring.mission.model.entity.Category;
import com.dku.mentoring.mission.model.entity.Mission;
import com.dku.mentoring.mission.model.entity.MissionBonus;
import com.dku.mentoring.mission.model.entity.MissionInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MissionResponseDto {
    private Long id;
    private String name;
    private String description;
    private int point;
    private String  category;
    private List<MissionBonusResponseDto> bonusList;
    private String info;

    @Builder
    public MissionResponseDto(Mission mission) {
        this.id = mission.getId();
        this.name = mission.getName();
        this.description = mission.getDescription();
        this.point = mission.getPoint();
        this.category = mission.getCategory().getName();
        this.bonusList = MissionBonusResponseDto.listOf(mission.getBonusList());
        this.info = mission.getInfo().getName();
    }
}
