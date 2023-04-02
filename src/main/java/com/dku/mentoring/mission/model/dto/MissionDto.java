package com.dku.mentoring.mission.model.dto;

import com.dku.mentoring.mission.model.entity.Category;
import com.dku.mentoring.mission.model.entity.Mission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MissionDto {
    private Long id;
    private String description;
    private int point;
    private Category category;

    @Builder
    public MissionDto(Mission mission) {
        this.id = mission.getId();
        this.description = mission.getDescription();
        this.point = mission.getPoint();
        this.category = mission.getCategory();
    }
}
