package org.sangyunpark99.auth.repository.jpa;

import org.sangyunpark99.auth.repository.entity.EmailVerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaEmailVerificationRepository extends JpaRepository<EmailVerificationEntity, Long> {
    Optional<EmailVerificationEntity> findByEmail(String email);
}
