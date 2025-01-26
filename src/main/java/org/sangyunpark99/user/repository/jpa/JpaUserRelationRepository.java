package org.sangyunpark99.user.repository.jpa;

import org.sangyunpark99.user.repository.entity.UserRelationIdEntity;
import org.sangyunpark99.user.repository.entity.UserRelationshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationshipEntity, UserRelationIdEntity> {

    @Query("SELECT u.followerUserId FROM UserRelationshipEntity u WHERE u.followingUserId = :userId")
    List<Long> findFollowers(Long userId);
}
