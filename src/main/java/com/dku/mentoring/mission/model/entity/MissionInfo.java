package com.dku.mentoring.mission.model.entity;

public enum MissionInfo {
    VERY_EASY("최하"),
    EASY("하"),
    MEDIUM("중"),
    MEDIUM_HARD("중상"),
    HARD("상"),
    VERY_HARD("최상");

    private final String name;

    MissionInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
