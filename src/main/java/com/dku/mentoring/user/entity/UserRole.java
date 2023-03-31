package com.dku.mentoring.user.entity;

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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String rolename;

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
