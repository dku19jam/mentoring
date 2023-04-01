package com.dku.mentoring.mission.model.dto;

import com.dku.mentoring.mission.model.entity.Category;
import com.dku.mentoring.mission.model.entity.Mission;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MissionDto {
    private Long id;
    private String description;
    private int point;
    private Category category;

    @Builder
    public MissionDto(Mission mission) {
        this.description = mission.getDescription();
        this.point = mission.getPoint();
        this.category = mission.getCategory();
    }
}