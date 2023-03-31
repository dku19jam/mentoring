package com.dku.mentoring.register.repository;

import com.dku.mentoring.register.model.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RegisterRepository<T extends Register> extends JpaRepository<Register, Long>, JpaSpecificationExecutor<T> {
    @Query("select r from Register r where r.id = :id")
    Optional<Register> findById(Long id);
}
