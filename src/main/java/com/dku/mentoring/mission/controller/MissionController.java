package com.dku.mentoring.mission.controller;

import com.dku.mentoring.mission.model.dto.request.BonusMissionCreateRequestDto;
import com.dku.mentoring.mission.model.dto.request.MissionCreateRequestDto;
import com.dku.mentoring.mission.model.dto.response.*;
import com.dku.mentoring.mission.service.MissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(("/missions"))
public class MissionController {

    private final MissionService missionService;


    @Operation(summary = "전체 미션 조회", responses = {@ApiResponse(responseCode = "200", description = "전체 미션 조회 성공")})
    @GetMapping
    public MissionResponsePage<MissionResponseDto> getMissions(@RequestParam(defaultValue = "1") int page,
                                                               @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        MissionResponsePage<MissionResponseDto> missions = missionService.getMissions(pageable);
        return missions;
    }

    @Operation(summary = "미션 검색", responses = {@ApiResponse(responseCode = "200", description = "미션 검색 성공")})
    @GetMapping("/search")
    public MissionResponsePage<MissionResponseDto> getMissionsByDescription(@RequestParam(defaultValue = "1") int page,
                                                                            @RequestParam(defaultValue = "10") int size,
                                                                            @RequestParam String keyword) {
        Pageable pageable = PageRequest.of(page - 1, size);
        MissionResponsePage<MissionResponseDto> mission = missionService.getMissionsByDescription(keyword, pageable);
        return mission;
    }

    @Operation(summary = "미션 상세 조회", responses = {@ApiResponse(responseCode = "200", description = "미션 상세 조회 성공")})
    @GetMapping("/{missionId}")
    public SingleMissionResponseDto getMission(@PathVariable Long missionId) {
        return missionService.getMission(missionId);
    }

    @Operation(summary = "미션 등록", description = "미션 등록")
    @PostMapping("/create")
    public ResponseEntity<Long> createMission(@RequestBody MissionCreateRequestDto dto, HttpServletRequest request) {
        Long missionId = missionService.createMission(dto, request);
        return ResponseEntity.ok().body(missionId);
    }

    @Operation(summary = "미션 난이도 조회", description = "미션 난이도 조회")
    @GetMapping("/difficulty")
    public List<ResponseDifficultyListDto> getDifficulty() {
        return missionService.getDifficulty();
    }

    @Operation(summary = "미션 난이도별 조회", description = "미션 난이도별 조회")
    @GetMapping("/difficulty/{difficultyId}")
    public MissionResponsePage<MissionResponseDto> getMissionByDifficulty(@PathVariable String difficultyId,
                                                                          @RequestParam(defaultValue = "1") int page,
                                                                          @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        MissionResponsePage<MissionResponseDto> missions = missionService.getMissionsByDifficultyId(difficultyId, pageable);
        return missions;
    }

    @Operation(summary = "추가 미션 등록", description = "추가 미션 등록")
    @PostMapping("/create/bonus")
    public ResponseEntity<Long> createBonusMission(@RequestBody BonusMissionCreateRequestDto dto, HttpServletRequest request) {
        Long bonusMissionId = missionService.createBonusMission(dto, request);
        return ResponseEntity.ok().body(bonusMissionId);
    }
}
