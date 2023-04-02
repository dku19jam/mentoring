package com.dku.mentoring.register.model.entity;

import com.dku.mentoring.base.BaseEntity;
import com.dku.mentoring.mission.model.entity.Mission;
import com.dku.mentoring.team.model.entity.Team;
import com.dku.mentoring.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@DynamicUpdate
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

    @Enumerated(EnumType.STRING)
    private RegisterStatus status;

//    @OneToMany(mappedBy = "register", cascade = CascadeType.ALL)
//    private List<RegisterFile> files = new ArrayList<>();

    @Builder
    public Register(User user, String title, String body, Mission mission, RegisterStatus status) {
        this.user = user;
        this.title = title;
        this.body = body;
        this.mission = mission;
        this.status = status;
    }
}
