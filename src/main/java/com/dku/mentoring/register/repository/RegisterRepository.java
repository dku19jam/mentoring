package com.dku.mentoring.register.repository;

import com.dku.mentoring.register.model.entity.Register;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<Register, Long>{

    Page<Register> findByUserId(Long userId, Pageable pageable);
}