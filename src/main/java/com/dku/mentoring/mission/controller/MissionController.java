package com.dku.mentoring.mission.controller;

import com.dku.mentoring.global.base.dto.request.ResponseIdDto;
import com.dku.mentoring.mission.model.dto.request.MissionCreateRequestDto;
import com.dku.mentoring.mission.model.dto.response.MissionResponseDto;
import com.dku.mentoring.mission.model.dto.response.MissionResponsePage;
import com.dku.mentoring.mission.model.dto.response.SingleMissionResponseDto;
import com.dku.mentoring.mission.model.entity.Mission;
import com.dku.mentoring.mission.service.MissionService;
import com.dku.mentoring.register.model.dto.request.RegisterRequestDto;
import com.dku.mentoring.register.service.RegisterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(("/missions"))
public class MissionController {

    private final MissionService missionService;
    private final RegisterService registerService;


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
    public ResponseEntity<Long> createMission(@RequestBody MissionCreateRequestDto dto) {
        Long missionId = missionService.createMission(dto);
        return ResponseEntity.ok().body(missionId);
    }


}
