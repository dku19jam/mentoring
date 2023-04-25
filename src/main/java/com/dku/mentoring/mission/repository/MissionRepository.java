package com.dku.mentoring.mission.repository;

import com.dku.mentoring.mission.model.entity.Mission;
import com.dku.mentoring.mission.model.entity.MissionInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MissionRepository extends JpaRepository<Mission, Long>{
    Page<Mission> findByDescriptionContaining(String description, Pageable pageable);

    Page<Mission> findByInfo(MissionInfo infoId, Pageable pageable);

}
