package com.dku.mentoring.mission.service;

import com.dku.mentoring.mission.exception.MissionNotFoundException;
import com.dku.mentoring.mission.model.dto.request.MissionCreateRequestDto;
import com.dku.mentoring.mission.model.dto.response.*;
import com.dku.mentoring.mission.model.entity.Mission;
import com.dku.mentoring.mission.model.entity.MissionBonus;
import com.dku.mentoring.mission.model.entity.MissionInfo;
import com.dku.mentoring.mission.repository.MissionBonusRepository;
import com.dku.mentoring.mission.repository.MissionRepository;
import com.dku.mentoring.register.model.entity.Register;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


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

    /**
     * 미션 난이도 조회
     * <p>
     * 미션 등록 리스트에서 난이도를 선택해서 등록할 수 있게끔 하기 위해서 구현
     */
    public List<ResponseDifficultyListDto> getInfo() {
        List<MissionInfo> missionInfos = Arrays.asList(MissionInfo.values());
        return missionInfos.stream()
                .map(ResponseDifficultyListDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 미션 난이도별 조회
     *
     * @param infoId 난이도
     */
    public MissionResponsePage<MissionResponseDto> getMissionsByInfoId(String infoId, Pageable pageable) {
        MissionInfo info = MissionInfo.valueOf(infoId);
        Page<MissionResponseDto> missions = missionRepository.findByInfo(info, pageable).map(MissionResponseDto::new);
        return new MissionResponsePage<>(missions);
    }
}
