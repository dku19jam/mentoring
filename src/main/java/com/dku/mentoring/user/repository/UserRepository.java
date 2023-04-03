package com.dku.mentoring.user.repository;

import com.dku.mentoring.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByStudentId(String studentId);

    Optional<User> findByTeamId(Long teamId);
}
