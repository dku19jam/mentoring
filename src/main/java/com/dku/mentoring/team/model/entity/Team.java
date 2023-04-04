package com.dku.mentoring.team.model.entity;

import com.dku.mentoring.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "team_id", nullable = false)
    private Long id;

    @OneToOne(mappedBy = "team",cascade = CascadeType.ALL, orphanRemoval = true)
    private User user;

    private String teamName;

    private String mentee;

    private int score;

    @Builder
    public Team(User user, String teamName, String mentee) {
        this.user = user;
        this.teamName = teamName;
        this.score = 0;
        this.mentee = mentee;
    }

    public void addScore(int point) {
        this.score += point;
    }


}
