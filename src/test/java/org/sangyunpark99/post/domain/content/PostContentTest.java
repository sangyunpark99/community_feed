package org.sangyunpark99.post.domain.content;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostContentTest {

    @Test
    @DisplayName("givenUnderMinLengthContent_whenCreated_thenThrowError")
    void givenContentLengthIsOk_whenCreated_thenReturnTextContent() throws Exception{
        //given
        String text = "aaaaaa";

        //when
        PostContent content = new PostContent(text);

        assertEquals(text, content.getContentText());
    }

    @Test
    @DisplayName("givenUnderMinLengthContent_whenCreated_thenThrowError")
    void givenContentLengthIsUnder_whenCreated_thenThrowError() {
        //given
        String content = "a";

        //when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @Test
    @DisplayName("givenFiveOverMaxLengthContent_whenCreated_thenThrowError")
    void givenContentLengthIsOver_whenCreated_thenThrowError() {
        //given
        String text = "a".repeat(501);

        //when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(text));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁","닭","굵","궯","쁇"})
    @DisplayName("givenContentLengthIsOkAndKorean_whenCreated_thenReturnTextContent")
    void givenContentLengthIsOkAndKorean_whenCreated_thenReturnTextContent(String korean) {
        //given
        String text = korean.repeat(100);

        //when
        PostContent content = new PostContent(text);

        assertEquals(text, content.getContentText());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentIsEmpty_whenCreated_thenReturnTextContent(String text) {
        //when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(text));
    }

    @Test
    void givenContentLengthIsOk_whenUpdated_thenReturnUpdatedContent() {
        //given
        String content = "this is a text content";
        PostContent postContent = new PostContent(content);

        // when
        String updatedContent = "this is a updated content";
        postContent.updateContent(updatedContent);

        // then
        assertEquals(updatedContent, postContent.getContentText());
    }

    @Test
    void givenContentLengthOver_whenUpdated_thenReturnUpdatedContent() {
        //given
        String content = "this is a text content";
        PostContent postContent = new PostContent(content);

        // when
        String updatedContent = "a".repeat(501);

        // then
        assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(updatedContent));
    }

    @Test
    void givenContentLengthUnder_whenUpdated_thenReturnUpdatedContent() {
        //given
        String content = "this is a text content";
        PostContent postContent = new PostContent(content);

        // when
        String updatedContent = "a".repeat(4);

        // then
        assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(updatedContent));
    }
}
