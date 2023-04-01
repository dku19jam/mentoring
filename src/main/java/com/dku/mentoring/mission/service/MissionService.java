package com.dku.mentoring.mission.service;

import com.dku.mentoring.base.ResponsePage;
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

    public MissionResponsePage<MissionDto> getMissions(Pageable pageable) {
        Page<MissionDto> missions = missionRepository.findAll(pageable).map(MissionDto::new);
        return new MissionResponsePage<>(missions);
    }

    public MissionResponsePage<MissionDto> getMissionsByDescription(String description, Pageable pageable) {
        Page<MissionDto> missions = missionRepository.findByDescriptionContaining(description, pageable).map(MissionDto::new);
        return new MissionResponsePage<>(missions);
    }
}
