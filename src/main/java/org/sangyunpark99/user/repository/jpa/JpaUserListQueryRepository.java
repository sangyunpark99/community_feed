package org.sangyunpark99.user.repository.jpa;

import org.sangyunpark99.user.application.dto.response.GetUserListResponseDto;
import org.sangyunpark99.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface JpaUserListQueryRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT new org.sangyunpark99.user.application.dto.response.GetUserListResponseDto(u.name, u" +
            ".profileImageUrl)  FROM " +
            "UserRelationshipEntity ur " +
            "INNER JOIN UserEntity u ON ur.followerUserId = u.id " +
            "WHERE ur.followingUserId =:userId")
    List<GetUserListResponseDto> getUserFollowerList(Long userId); // userId의 팔로워

    @Query(value = "SELECT new org.sangyunpark99.user.application.dto.response.GetUserListResponseDto(u.name, u" +
            ".profileImageUrl) FROM UserRelationshipEntity ur " +
            "INNER JOIN UserEntity u ON ur.followingUserId = u.id " +
            "WHERE ur.followerUserId =:userId")
    List<GetUserListResponseDto> getUserFolowingList(Long userId); // userId가 팔로잉하고 있는 유저 목록
}
