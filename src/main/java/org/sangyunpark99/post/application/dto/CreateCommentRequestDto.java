package org.sangyunpark99.post.application.dto;

public record CreateCommentRequestDto(String content, Long postId, Long userId) {
}
