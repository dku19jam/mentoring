package com.dku.mentoring.mission.model.entity;

import com.dku.mentoring.global.base.BaseEntity;
import com.dku.mentoring.register.model.entity.Register;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Mission extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "mission_id")
    private Long id;

    private String name;

    private String description;

    @Enumerated(STRING)
    private MissionInfo info;

    @OneToMany(mappedBy = "mission", cascade = ALL, orphanRemoval = true)
    private List<MissionBonus> bonusList = new ArrayList<>();

    private int point;

    @Enumerated(STRING)
    private Category category;

    /*
    - TODO 1. 미션 난이도별 API 구현 → 중 , 중상 , 상 은 타과 연합 가능 → 기능 구현할 때 boolean으로 확인해야 할듯?
     */

    @OneToMany(mappedBy = "mission", cascade = ALL, orphanRemoval = true)
    private List<Register> registers = new ArrayList<>();


    @Builder
    public Mission(Long id, String name, String description, int point, Category category, MissionInfo info, List<MissionBonus> missionBonusList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.point = point;
        this.category = category;
        this.info = info;
        this.bonusList = missionBonusList;
    }

    public void addRegister(Register register) {
        this.registers.add(register);
        register.setMission(this);
    }
}
