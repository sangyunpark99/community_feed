package org.sangyunpark99.auth.repository.jpa;

import org.sangyunpark99.auth.repository.entity.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserAuthRepository extends JpaRepository<UserAuthEntity, String> {

}
