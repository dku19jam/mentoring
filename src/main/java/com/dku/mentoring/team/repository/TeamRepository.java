package com.dku.mentoring.team.repository;

import com.dku.mentoring.team.model.entity.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

    /**
     * 팀 이름을 일정부분만 검색해도 조회 될 수 있게
     * @return 팀 리스트
     */
    Page<Team> findByTeamNameLikeIgnoreCase(String teamName, Pageable pageable);

}
