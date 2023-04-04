package com.dku.mentoring.mission.repository;

import com.dku.mentoring.mission.model.entity.MissionBonus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MissionBonusRepository extends JpaRepository<MissionBonus, Long> {
    List<MissionBonus> findAllByMissionName(String name);

    List<MissionBonus> findAllByMissionId(Long id);
}
