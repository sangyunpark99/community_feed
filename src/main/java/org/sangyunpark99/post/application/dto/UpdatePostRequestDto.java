package org.sangyunpark99.post.application.dto;

import org.sangyunpark99.post.domain.content.PostState;

public record UpdatePostRequestDto(Long userId, String content, PostState state) {
}
