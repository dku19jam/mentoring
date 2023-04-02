package com.dku.mentoring.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name="roles")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRole implements Serializable
{
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    private String rolename;

    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserRole memberRole = (UserRole) o;
        return Objects.equals(user, memberRole.user) && Objects.equals(rolename, memberRole.rolename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, rolename);
    }
}
