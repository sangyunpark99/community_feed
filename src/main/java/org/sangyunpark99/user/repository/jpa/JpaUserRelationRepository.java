package org.sangyunpark99.user.repository.jpa;

import org.sangyunpark99.user.repository.entity.UserRelationIdEntity;
import org.sangyunpark99.user.repository.entity.UserRelationshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationshipEntity, UserRelationIdEntity> {
}
