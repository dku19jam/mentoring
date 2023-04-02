package com.dku.mentoring.team.model.entity;

import com.dku.mentoring.register.model.entity.Register;
import com.dku.mentoring.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "team_id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users = new ArrayList<>();

    private String teamName;

    private String mentee;

    private int score;

    @Builder
    public Team(List<User> users, String teamName, String mentee, int score) {
        this.users = users;
        this.teamName = teamName;
        this.mentee = mentee;
        this.score = score;
    }
}
