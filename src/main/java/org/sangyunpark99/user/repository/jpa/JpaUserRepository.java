package org.sangyunpark99.user.repository.jpa;

import org.sangyunpark99.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    @Modifying
    @Query(value = "UPDATE UserEntity u SET u.name = :#{#userEntity.getName()}, u" +
            ".profileImageUrl = :#{#userEntity.getProfileImageUrl()}, " +
            "u.followerCount = :#{#userEntity.followerCount}," +
            "u.followingCount = :#{#userEntity.followingCount}," +
            "u.modDate = now() " +
            "WHERE u.id = :#{#userEntity.getId()} "
    )
    void updateUserEntity(UserEntity userEntity);
}
