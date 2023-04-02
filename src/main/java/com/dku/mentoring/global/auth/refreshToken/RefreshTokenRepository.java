package com.dku.mentoring.global.auth.refreshToken;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author 최재민
 */
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
    Optional<RefreshToken> findByMemberId(String memberId);
}
