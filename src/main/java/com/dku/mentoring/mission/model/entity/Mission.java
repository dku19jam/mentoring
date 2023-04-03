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

    //TODO name 추가 -> 미션 이름

    //TODO description 변경 -> 미션 내용 (미션 수행 방법 등 추가 예정)
    private String description;

    private int point;

    private Category category;

    /*
    - TODO 미션 앤티티 변경 (미션이름, 미션내용) → 기능 변경
    - TODO 미션 난이도별 API 구현 → 중 , 중상 , 상 은 타과 연합 가능 → 기능 구현할 때 boolean으로 확인해야 할듯?
     */

    private MissionInfo info;

    @OneToMany(mappedBy = "mission", cascade = ALL, orphanRemoval = true)
    private List<Register> registers = new ArrayList<>();


    @Builder
    public Mission(Long id, String description, int point, Category category, MissionInfo info) {
        this.id = id;
        this.description = description;
        this.point = point;
        this.category = category;
    }
}
