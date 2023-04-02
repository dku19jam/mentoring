package com.dku.mentoring.team.repository;

import com.dku.mentoring.team.model.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByTeamName(String teamName);

    Optional<Team> findById(Long id);
}
