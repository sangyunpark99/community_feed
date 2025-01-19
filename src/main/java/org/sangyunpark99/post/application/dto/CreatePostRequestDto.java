package org.sangyunpark99.post.application.dto;

import org.sangyunpark99.post.domain.content.PostState;

public record CreatePostRequestDto(Long userId, String content, PostState state) {
}
