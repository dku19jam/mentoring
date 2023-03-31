package com.dku.mentoring.mission.model.entity;

import com.dku.mentoring.base.BaseEntity;
import com.dku.mentoring.register.model.entity.Register;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Mission extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "mission_id")
    private Long id;

    private String description;

    private int point;

    private MissionStatus status;

    @OneToMany(mappedBy = "mission", cascade = ALL, orphanRemoval = true)
    private List<Register> registers = new ArrayList<>();
}
