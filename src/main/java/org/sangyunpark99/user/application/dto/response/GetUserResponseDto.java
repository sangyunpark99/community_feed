package org.sangyunpark99.user.application.dto.response;

import org.sangyunpark99.user.domain.User;

public record GetUserResponseDto(Long id, String name, String profileImage, Integer followingCount,
                                 Integer followerCount) {

    public GetUserResponseDto(User user) {
        this(user.getId(), user.getName(), user.getProfileImageUrl(), user.getFollowingCount(), user.getFollowerCount());
    }
}
