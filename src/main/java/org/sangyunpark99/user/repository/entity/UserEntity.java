package org.sangyunpark99.user.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.sangyunpark99.common.domain.PositiveIntegerCounter;
import org.sangyunpark99.common.repository.entity.TimeBaseEntity;
import org.sangyunpark99.user.domain.User;
import org.sangyunpark99.user.domain.UserInfo;

@Entity
@Table(name = "community_user")
@NoArgsConstructor
@Getter
@DynamicUpdate
public class UserEntity extends TimeBaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String profileImageUrl;

    private Integer followingCount;

    private Integer followerCount;

    public UserEntity(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.profileImageUrl = user.getProfileImageUrl();
        this.followingCount = user.getFollowingCount();
        this.followerCount = user.getFollowerCount();
    }

    public User toUser() {
        return User.builder()
                .id(id)
                .info(new UserInfo(name, profileImageUrl))
                .followingCount(new PositiveIntegerCounter(followingCount))
                .followerCount(new PositiveIntegerCounter(followerCount))
                .build();
    }
}
