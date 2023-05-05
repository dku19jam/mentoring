package com.dku.mentoring.mission.model.dto.response;

import com.dku.mentoring.mission.model.entity.Mission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MissionResponseDto {
    private Long id;
    private String name;
    private String description;
    private int point;
    private String  category;
    private String difficulty;

    @Builder
    public MissionResponseDto(Mission mission) {
        this.id = mission.getId();
        this.name = mission.getName();
        this.description = mission.getDescription();
        this.point = mission.getPoint();
        this.category = mission.getCategory().getName();
        this.difficulty = mission.getDifficulty().getName();
    }
}
