package com.dku.mentoring.team.repository;

import com.dku.mentoring.team.model.entity.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("select t from Team t where t.teamName != '관리자팀' order by t.score desc ")
    Page<Team> findAllDesc(Pageable pageable);
    Optional<Team> findById(Long id);

    Optional<Team> findByTeamName(String teamName);
}
