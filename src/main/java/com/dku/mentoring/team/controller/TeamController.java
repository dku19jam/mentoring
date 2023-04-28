package com.dku.mentoring.team.controller;

import com.dku.mentoring.global.base.dto.response.ResponsePage;
import com.dku.mentoring.team.model.dto.list.SummarizedTeamDto;
import com.dku.mentoring.team.model.dto.reponse.TeamSpecResponseDto;
import com.dku.mentoring.team.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;

    @Operation(summary = "전체 팀 조회", responses = {@ApiResponse(responseCode = "200", description = "전체 팀 조회 성공")})
    @GetMapping
    public ResponsePage<SummarizedTeamDto> getTeams(@RequestParam(defaultValue = "1") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<SummarizedTeamDto> teams = teamService.getTeams(pageable);
        return new ResponsePage<>(teams);
    }

    @Operation(summary = "팀 상세 조회", responses = {@ApiResponse(responseCode = "200", description = "팀 상세 조회 성공")})
    @GetMapping("/{teamId}")
    public TeamSpecResponseDto getTeam(@PathVariable Long teamId) {
        return teamService.getTeam(teamId);
    }

}