package org.sangyunpark99.user.repository.jpa;

import org.sangyunpark99.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

}
