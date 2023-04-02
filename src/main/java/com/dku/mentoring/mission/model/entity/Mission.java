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

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Mission extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "mission_id")
    private Long id;

    private String description;

    private int point;

    private Category category;

    @OneToMany(mappedBy = "mission", cascade = ALL, orphanRemoval = true)
    private List<Register> registers = new ArrayList<>();


    @Builder
    public Mission(Long id, String description, int point, Category category) {
        this.id = id;
        this.description = description;
        this.point = point;
        this.category = category;
    }
}
