package org.sangyunpark99.user.repository.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.sangyunpark99.common.repository.entity.TimeBaseEntity;

@Entity
@Table(name = "community_user_relation")
@NoArgsConstructor
@IdClass(UserRelationIdEntity.class)
public class UserRelationshipEntity extends TimeBaseEntity {

    @Id
    private Long followingUserId; // 어떤 아이디를 팔로잉 하나요

    @Id
    private Long followerUserId; // 전 팔로워 유저 입니다.
}
