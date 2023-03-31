package com.dku.mentoring.register.model.entity;

import com.dku.mentoring.base.BaseEntity;
import com.dku.mentoring.mission.model.entity.Mission;
import com.dku.mentoring.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Enumerated(EnumType.STRING)
    private RegisterStatus status;

    @OneToMany(mappedBy = "register", cascade = CascadeType.ALL)
    private List<RegisterFile> files;

    public Register(User user, String title, String body) {
        this.user = user;
        this.title = title;
        this.body = body;
    }

}
