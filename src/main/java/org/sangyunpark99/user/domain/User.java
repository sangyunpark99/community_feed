package org.sangyunpark99.user.domain;

import org.sangyunpark99.post.domain.common.PositiveIntegerCounter;

import java.util.Objects;

public class User {

    private final Long id;
    private final UserInfo info;
    private final PositiveIntegerCounter followingCount;
    private final PositiveIntegerCounter followerCount;

    public User(Long id, UserInfo userInfo) {

        if(userInfo == null) { // 객체가 null인 부분은 항상 검사하기
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.info = userInfo;
        this.followerCount = new PositiveIntegerCounter();
        this.followingCount = new PositiveIntegerCounter();
    }

    public Long getId() {
        return id;
    }

    public UserInfo getInfo() {
        return info;
    }

    public int getFollowingCount() {
        return followingCount.getCount();
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
