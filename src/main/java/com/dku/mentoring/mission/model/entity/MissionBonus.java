package com.dku.mentoring.mission.model.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MissionBonus {

    @Id @GeneratedValue
    @Column(name = "mission_bonus_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    private String plusMission;

    private int plusPoint;

    @Builder
    public MissionBonus(String plusMission, int plusPoint) {
        this.plusMission = plusMission;
        this.plusPoint = plusPoint;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }
}
