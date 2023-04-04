package com.dku.mentoring.team.service;

import com.dku.mentoring.register.model.entity.RegisterStatus;
import com.dku.mentoring.team.model.entity.Team;
import com.dku.mentoring.team.model.dto.list.SummarizedTeamDto;
import com.dku.mentoring.team.model.dto.reponse.TeamSpecResponseDto;
import com.dku.mentoring.team.repository.TeamRepository;
import com.dku.mentoring.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    @Transactional
    public Team createTeam(String teamName){
        return teamRepository.save(Team.builder().teamName(teamName).build());
    }

    /**
     * 전체 팀 조회
     *
     */
     public Page<SummarizedTeamDto> getTeams(Pageable pageable){
         Page<Team> teams= teamRepository.findAllDesc(pageable);
         return teams.map(SummarizedTeamDto::new);
     }

    /**
     *  팀 상세 조회
     *
     *  @param teamId 조회할 팀 id
     */
    public TeamSpecResponseDto getTeam(Long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new IllegalArgumentException("해당 팀이 없습니다."));
        List<String> missions = team.getUser().getRegisters().stream()
                .filter(register -> register.getStatus().equals(RegisterStatus.COMPLETE))
                .map(register -> register.getMission().getDescription())
                .collect(Collectors.toList());
        return new TeamSpecResponseDto(team, missions);
    }
}
