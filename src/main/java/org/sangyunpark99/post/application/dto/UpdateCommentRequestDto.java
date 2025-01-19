package org.sangyunpark99.post.application.dto;

public record UpdateCommentRequestDto(Long commentId, Long userId, String content) {
}
