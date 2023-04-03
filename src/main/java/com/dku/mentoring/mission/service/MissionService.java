package com.dku.mentoring.mission.service;

import com.dku.mentoring.mission.model.dto.MissionDto;
import com.dku.mentoring.mission.model.dto.MissionResponsePage;
import com.dku.mentoring.mission.model.entity.Mission;
import com.dku.mentoring.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    /**
     * 전체 미션 조회
     *
     * @param pageable 페이징 정보
     */
    public MissionResponsePage<MissionDto> getMissions(Pageable pageable) {
        Page<MissionDto> missions = missionRepository.findAll(pageable).map(MissionDto::new);
        return new MissionResponsePage<>(missions);
    }

    /**
     * 미션 조회
     *
     * @param description 키워드
     * @param pageable    페이징 정보
     */
    public MissionResponsePage<MissionDto> getMissionsByDescription(String description, Pageable pageable) {
        Page<MissionDto> missions = missionRepository.findByDescriptionContaining(description, pageable).map(MissionDto::new);
        return new MissionResponsePage<>(missions);
    }


    /**
     * 미션 생성
     *
     * @param dto 미션 정보
     */
    public Long createMission(MissionDto dto) {
        Mission newMission = dto.toEntity();

        missionRepository.save(newMission);

        return newMission.getId();
    }
}
