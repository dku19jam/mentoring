package com.dku.mentoring.user.repository;

import com.dku.mentoring.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
