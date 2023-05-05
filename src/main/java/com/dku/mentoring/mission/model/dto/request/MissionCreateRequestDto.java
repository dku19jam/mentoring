package com.dku.mentoring.mission.model.dto.request;

import com.dku.mentoring.mission.model.entity.Category;
import com.dku.mentoring.mission.model.entity.Mission;
import com.dku.mentoring.mission.model.entity.MissionBonus;
import com.dku.mentoring.mission.model.entity.MissionDifficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MissionCreateRequestDto {
    private String name;
    private String description;
    private int point;
    private Category category;
    private MissionDifficulty difficulty;

    public MissionCreateRequestDto(String name, String description, int point, Category category, List<MissionBonus> bonusList, MissionDifficulty difficulty) {
        this.name = name;
        this.description = description;
        this.point = point;
        this.category = category;
        this.difficulty = difficulty;
    }

    public Mission toEntity() {
        return Mission.builder()
                .name(name)
                .description(description)
                .point(point)
                .category(category)
                .difficulty(difficulty)
                .build();
    }
}
