package com.dku.mentoring.register.repository;

import com.dku.mentoring.register.model.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisterRepository extends JpaRepository<Register, Long>{

}