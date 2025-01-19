package org.sangyunpark99.user.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sangyunpark99.post.domain.content.PostContent;

class PostContentTest {

    @Test
    @DisplayName("givenUnderMinLengthContent_whenCreated_thenThrowError")
    void givenContent_whenCreated_thenThrowNothing() throws Exception{
        //given
        String content = "aaaaaa";

        //when, then
        Assertions.assertDoesNotThrow(() -> new PostContent(content));
    }

    @Test
    @DisplayName("givenUnderMinLengthContent_whenCreated_thenThrowError")
    void givenUnderMinLengthContent_whenCreated_thenThrowError() throws Exception{
        //given
        String content = "a";

        //when, then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @Test
    @DisplayName("givenFiveOverMaxLengthContent_whenCreated_thenThrowError")
    void givenFiveOverMaxLengthContent_whenCreated_thenThrowError() throws Exception{
        //given
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i <= 500; i++) {
            sb.append("a");
        }

        //when, then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PostContent(sb.toString()));
    }
}
