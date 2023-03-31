package com.dku.mentoring.user.entity;

import com.dku.mentoring.team.model.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 최재민
 */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id @GeneratedValue
    private Long userId;

    private String password;

    private String name;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> roles = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public User(Long userId, String password, String name) {
        this.userId = userId;
        this.password = password;
        this.name = name;
    }

    public void addRole(UserRole role) {
        this.roles.add(role);
    }

}
