package org.sangyunpark99.common;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.sangyunpark99.post.domain.content.CommentContent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommentContentTest {
    @Test
    void givenContentLengthIsOk_whenCreateCommentContent_thenReturnTextContent() {
        // Given
        String content = "This is a comment content.";

        // When
        CommentContent commentContent = new CommentContent(content);

        // Then
        assertEquals(content, commentContent.getContentText());
    }

    @Test
    void givenContentLengthIsOver_whenCreateCommentContent_thenThrowIllegalArgumentException() {
        // Given
        String content = "a".repeat(101);

        // when,then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁", "닭", "굵"})
    void givenContentLengthIsOverAndKorean_whenCreateCommentContent_thenThrowIllegalArgumentException(String KoreanContent) {
        // Given
        String content = KoreanContent.repeat(101);

        // when,then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentIsNullOrEmpty_whenCreateCommentContent_thenThrowIllegalArgumentException(String content) {
        // when,then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }
}
