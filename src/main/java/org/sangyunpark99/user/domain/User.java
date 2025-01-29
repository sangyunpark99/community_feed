package org.sangyunpark99.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.sangyunpark99.common.domain.PositiveIntegerCounter;

import java.util.Objects;

@Builder
@AllArgsConstructor
public class User {

    private Long id;

    private UserInfo info;

    private PositiveIntegerCounter followingCount;

    private PositiveIntegerCounter followerCount;

    public User(Long id, UserInfo userInfo) {

        if(userInfo == null) { // 객체가 null인 부분은 항상 검사하기
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.info = userInfo;
        this.followerCount = new PositiveIntegerCounter();
        this.followingCount = new PositiveIntegerCounter();
    }

    public User(String name, String profileImageUrl) {
        this(null, new UserInfo(name, profileImageUrl));
    }

    public Long getId() {
        return id;
    }

    public UserInfo getInfo() {
        return info;
    }

    public String getName() {
        return info.getName();
    }

    public int getFollowingCount() {
        return followingCount.getCount();
    }

    public String getProfileImageUrl() {
        return info.getProfileImageUrl();
    }

    public int getFollowerCount() {
        return followerCount.getCount();
    }

    public void follow(User targetUser) {
        if(targetUser.equals(this)) {
            throw new IllegalArgumentException();
        }

        increaseFollowingCount();
        targetUser.increaseFollowerCount();
    }

    public void unfollow(User targetUser) {
        if(targetUser.equals(this)) {
            throw new IllegalArgumentException();
        }

        decreaseFollowingCount();
        targetUser.decreaseFollowerCount();
    }

    private void increaseFollowingCount() {
        followingCount.increase();
    }

    private void decreaseFollowingCount() {
        followingCount.decrease();
    }

    private void increaseFollowerCount() {
        followerCount.increase();
    }

    private void decreaseFollowerCount() {
        followerCount.decrease();
    }

    @Override
    public boolean equals(Object o) { // id를 기준으로
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
