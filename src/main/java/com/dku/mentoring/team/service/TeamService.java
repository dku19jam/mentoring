package com.dku.mentoring.team.service;

import com.dku.mentoring.team.model.entity.Team;
import com.dku.mentoring.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    @Transactional
    public Team createTeam(String teamName){
        return teamRepository.save(Team.builder().teamName(teamName).build());
    }
}
