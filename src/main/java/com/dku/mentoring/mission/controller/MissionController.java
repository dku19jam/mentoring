package com.dku.mentoring.mission.controller;

import com.dku.mentoring.mission.model.dto.MissionDto;
import com.dku.mentoring.mission.model.dto.MissionResponsePage;
import com.dku.mentoring.mission.service.MissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "미션", description = "미션 관련 API")
public class MissionController {

    private final MissionService missionService;


    @Operation(summary = "전체 미션 조회", responses = {@ApiResponse(responseCode = "200", description = "전체 미션 조회 성공")})
    @GetMapping("/missions")
    public MissionResponsePage<MissionDto> getMissions(@RequestParam(defaultValue = "1") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        MissionResponsePage<MissionDto> missions = missionService.getMissions(pageable);
        return missions;
    }

    @Operation(summary = "미션 검색", responses = {@ApiResponse(responseCode = "200", description = "미션 검색 성공")})
    @GetMapping("/missions/search")
    public MissionResponsePage<MissionDto> getMissionsByDescription(@RequestParam(defaultValue = "1") int page,
                                                                    @RequestParam(defaultValue = "10") int size,
                                                                    @RequestParam String keyword) {
        Pageable pageable = PageRequest.of(page - 1, size);
        MissionResponsePage<MissionDto> mission = missionService.getMissionsByDescription(keyword, pageable);
        return mission;
    }

}
