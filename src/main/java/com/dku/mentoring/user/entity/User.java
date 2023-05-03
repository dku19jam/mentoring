package com.dku.mentoring.user.entity;

import com.dku.mentoring.register.model.entity.Register;
import com.dku.mentoring.team.model.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @Column(name ="user_id")
    private Long id;

    @NotNull
    @Column(unique = true)
    private String studentId;
    
    private String password;

    private String name;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Register> registers = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    private Team team;

    @Builder
    public User(String studentId, String password, String name, List<UserRole> roles) {
        this.studentId = studentId;
        this.password = password;
        this.name = name;
        this.roles = roles;
    }

    public void addRole(List<UserRole> role){
        this.roles = role;
        role.forEach(r -> r.setUser(this));
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void changePassword(String encodedNewPassword) {
        this.password = encodedNewPassword;
    }
}
