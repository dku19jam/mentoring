package com.dku.mentoring.mission.model.entity;

import lombok.Getter;

@Getter
public enum Category {
    STUDY("공부"),
    SPORT("운동"),
    CULTURE("문화"),
    VOLUNTEER("봉사"),
    ETC("기타");

    private String name;
    Category(String name) {
        this.name = name;
    }
}
