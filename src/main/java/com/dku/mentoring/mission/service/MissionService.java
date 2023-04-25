package com.dku.mentoring.mission.service;

import com.dku.mentoring.mission.exception.MissionNotFoundException;
import com.dku.mentoring.mission.model.dto.request.MissionCreateRequestDto;
import com.dku.mentoring.mission.model.dto.response.MissionBonusResponseDto;
import com.dku.mentoring.mission.model.dto.response.MissionResponseDto;
import com.dku.mentoring.mission.model.dto.response.MissionResponsePage;
import com.dku.mentoring.mission.model.dto.response.SingleMissionResponseDto;
import com.dku.mentoring.mission.model.entity.Mission;
import com.dku.mentoring.mission.model.entity.MissionBonus;
import com.dku.mentoring.mission.model.entity.MissionInfo;
import com.dku.mentoring.mission.repository.MissionBonusRepository;
import com.dku.mentoring.mission.repository.MissionRepository;
import com.dku.mentoring.register.model.entity.Register;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MissionRepository missionRepository;

    /**
     * 전체 미션 조회
     *
     * @param pageable 페이징 정보
     */
    public MissionResponsePage<MissionResponseDto> getMissions(Pageable pageable) {
        Page<MissionResponseDto> mission = missionRepository.findAll(pageable).map(MissionResponseDto::new);
        return new MissionResponsePage<>(mission);
    }

    /**
     * 미션 조회
     *
     * @param description 키워드
     * @param pageable    페이징 정보
     */
    public MissionResponsePage<MissionResponseDto> getMissionsByDescription(String description, Pageable pageable) {
        Page<MissionResponseDto> missions = missionRepository.findByDescriptionContaining(description, pageable).map(MissionResponseDto::new);
        return new MissionResponsePage<>(missions);
    }

    /**
     * 미션 상세 조회
     */
    public SingleMissionResponseDto getMission(Long id) {
        Mission mission = missionRepository.findById(id).orElseThrow(() -> new MissionNotFoundException("해당 미션이 없습니다."));
        return new SingleMissionResponseDto(mission);
    }


    /**
     * 미션 생성
     * MissionBonus는 직접 추가,,
     *
     * @param dto 미션 정보
     */

    @Transactional
    public Long createMission(MissionCreateRequestDto dto) {
        Mission newMission = dto.toEntity();

        missionRepository.save(newMission);
        return newMission.getId();
    }
}
