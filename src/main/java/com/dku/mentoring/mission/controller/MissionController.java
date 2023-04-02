package com.dku.mentoring.mission.controller;

import com.dku.mentoring.mission.model.dto.MissionDto;
import com.dku.mentoring.mission.model.dto.MissionResponsePage;
import com.dku.mentoring.mission.model.entity.Category;
import com.dku.mentoring.mission.service.MissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping(("/missions"))
@Tag(name = "미션", description = "미션 관련 API")
public class MissionController {

    private final MissionService missionService;


    @Operation(summary = "전체 미션 조회", responses = {@ApiResponse(responseCode = "200", description = "전체 미션 조회 성공")})
    @GetMapping
    public MissionResponsePage<MissionDto> getMissions(@RequestParam(defaultValue = "1") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        MissionResponsePage<MissionDto> missions = missionService.getMissions(pageable);
        return missions;
    }

    @Operation(summary = "미션 검색", responses = {@ApiResponse(responseCode = "200", description = "미션 검색 성공")})
    @GetMapping("/search")
    public MissionResponsePage<MissionDto> getMissionsByDescription(@RequestParam(defaultValue = "1") int page,
                                                                    @RequestParam(defaultValue = "10") int size,
                                                                    @RequestParam String keyword) {
        Pageable pageable = PageRequest.of(page - 1, size);
        MissionResponsePage<MissionDto> mission = missionService.getMissionsByDescription(keyword, pageable);
        return mission;
    }

    @Operation(summary = "미션 등록", description = "미션 등록")
    @PostMapping("/create")
    public ResponseEntity<Long> createMission(@RequestBody MissionDto dto) {
        Long missionId = missionService.createMission(dto);
        return ResponseEntity.ok().body(missionId);
    }

}
