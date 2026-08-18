package com.accounting.app.repasitory;

import com.accounting.app.models.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, Long> {
    Boolean existsByToken(String token);
    void deleteByExpiresAtBefore(LocalDateTime dateTime);
}
