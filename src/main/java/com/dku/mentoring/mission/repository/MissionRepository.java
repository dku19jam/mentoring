package com.dku.mentoring.mission.repository;

import com.dku.mentoring.mission.model.entity.Mission;
import com.dku.mentoring.mission.model.entity.MissionInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long>{
    Page<Mission> findByNameContaining(String keyword, Pageable pageable);

    Page<Mission> findByInfo(MissionInfo infoId, Pageable pageable);

}
