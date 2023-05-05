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
    private MissionDifficulty difficulty;

    @OneToMany(mappedBy = "mission", cascade = ALL, orphanRemoval = true)
    private List<MissionBonus> bonusList = new ArrayList<>();

    private int point;

    @Enumerated(STRING)
    private Category category;

    @OneToMany(mappedBy = "mission", cascade = ALL, orphanRemoval = true)
    private List<Register> registers = new ArrayList<>();

    @Builder
    public Mission(Long id, String name, String description, int point, Category category, MissionDifficulty difficulty, List<MissionBonus> missionBonusList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.point = point;
        this.category = category;
        this.difficulty = difficulty;
        this.bonusList = missionBonusList;
    }

    public void addRegister(Register register) {
        this.registers.add(register);
        register.setMission(this);
    }

    public void addBonus(MissionBonus missionBonus) {
        this.bonusList.add(missionBonus);
        missionBonus.setMission(this);
    }
}
