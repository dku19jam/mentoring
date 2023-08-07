package com.dku.mentoring.register.model.entity;

import com.dku.mentoring.global.base.BaseEntity;
import com.dku.mentoring.mission.model.entity.Mission;
import com.dku.mentoring.register.model.dto.request.RegisterRequestDto;
import com.dku.mentoring.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Register extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "register_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    private int totalScore;

    @Enumerated(EnumType.STRING)
    private RegisterStatus status;

    @OneToMany(mappedBy = "register", cascade = CascadeType.ALL)
    private List<RegisterFile> files = new ArrayList<>();

    @Builder
    public Register(User user, String title, String body, Mission mission, int totalScore) {
        this.user = user;
        this.title = title;
        this.body = body;
        this.mission = mission;
        this.totalScore = totalScore;
        this.status = RegisterStatus.PROGRESS;
    }

    public void update(RegisterRequestDto dto) {
        this.title = dto.getTitle();
        this.body = dto.getBody();
    }

    public void setFiles(List<RegisterFile> registerFiles) {
        this.files = registerFiles;
    }

    public void approve(int adminBonusPoint) {
        this.status = RegisterStatus.COMPLETE;
        if(adminBonusPoint != 0)
            this.getUser().getTeam().addScore(adminBonusPoint);
        this.getUser().getTeam().addScore(mission.getPoint());
        this.totalScore = this.getTotalPoint() + adminBonusPoint;
    }

    public int getTotalPoint() {
        return mission.getPoint();
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public void changeStatusToProgress() {
        this.status = RegisterStatus.PROGRESS;
        this.totalScore = mission.getPoint();
    }
}
