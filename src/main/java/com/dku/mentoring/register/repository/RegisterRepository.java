package com.dku.mentoring.register.repository;

import com.dku.mentoring.register.model.entity.Register;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RegisterRepository extends JpaRepository<Register, Long>{

    Page<Register> findByUserId(Long userId, Pageable pageable);

    @Query("select r from Register r order by r.createdAt DESC")
    Page<Register> findAllRegisters(Pageable pageable);

    @Query("select r from Register r where r.status = 'PROGRESS' order by r.createdAt ASC")
    Page<Register> findAllProgressRegisters(Pageable pageable);
}