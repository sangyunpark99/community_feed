package org.sangyunpark99.user.application.dto.response;

public record FollowUserRequestDto(Long userId, Long targetUserId) {
}
