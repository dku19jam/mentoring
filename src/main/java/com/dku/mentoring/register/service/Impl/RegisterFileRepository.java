package com.dku.mentoring.register.service.Impl;

import com.dku.mentoring.register.model.entity.RegisterFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterFileRepository extends JpaRepository<RegisterFile, Long> {
    void deleteByFileId(String fileId);
}
