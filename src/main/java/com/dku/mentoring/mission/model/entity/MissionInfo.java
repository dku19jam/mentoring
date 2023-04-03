package com.dku.mentoring.mission.model.entity;

public enum MissionInfo {
    VERY_EASY("매우 쉬움"),
    EASY("쉬움"),
    NORMAL("보통"),
    HARD("어려움"),
    VERY_HARD("매우 어려움");

    private final String name;

    MissionInfo(String name) {
        this.name = name;
    }
}
