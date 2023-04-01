package com.dku.mentoring.mission.repository;

import com.dku.mentoring.mission.model.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long>{
    Page<Mission> findByDescriptionContaining(String description, Pageable pageable);
}
