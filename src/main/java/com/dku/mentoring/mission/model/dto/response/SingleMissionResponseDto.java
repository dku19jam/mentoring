package com.dku.mentoring.mission.model.dto.response;

import com.dku.mentoring.mission.model.entity.Category;
import com.dku.mentoring.mission.model.entity.Mission;
import com.dku.mentoring.mission.model.entity.MissionInfo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SingleMissionResponseDto {
    private final Long id;

    private final String name;

    private final String description;

    private final MissionInfo info;

    private List<MissionBonusResponseDto> bonusList = new ArrayList<>();

    private final int point;

    private final Category category;

    public SingleMissionResponseDto(Mission mission) {
        this.id = mission.getId();
        this.name = mission.getName();
        this.description = mission.getDescription();
        this.info = mission.getInfo();
        this.bonusList = MissionBonusResponseDto.listOf(mission.getBonusList());
        this.point = mission.getPoint();
        this.category = mission.getCategory();
    }
}
