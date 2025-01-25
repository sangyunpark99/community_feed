package org.sangyunpark99.user.repository.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.sangyunpark99.common.repository.entity.TimeBaseEntity;

@Entity
@Table(name = "community_user_relation")
@NoArgsConstructor
@Builder
@IdClass(UserRelationIdEntity.class)
public class UserRelationshipEntity extends TimeBaseEntity {

    @Id
    private Long followingUserId; // 어떤 아이디를 팔로잉 하나요

    @Id
    private Long followerUserId; // 전 팔로워 유저 입니다.

    public UserRelationshipEntity(Long followerUserId, Long followingUserId) {
        this.followerUserId = followerUserId;
        this.followingUserId = followingUserId;
    }
}
