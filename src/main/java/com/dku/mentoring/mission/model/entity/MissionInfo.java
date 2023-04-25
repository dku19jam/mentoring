package com.dku.mentoring.mission.model.entity;

public enum MissionInfo {
    VERY_EASY("최하"),
    EASY("하"),
    NORMAL("중"),
    HARD("중상"),
    VERY_HARD("상");

    private final String name;

    MissionInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
