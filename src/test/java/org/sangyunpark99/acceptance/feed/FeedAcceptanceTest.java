package org.sangyunpark99.acceptance.feed;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sangyunpark99.acceptance.utils.AcceptanceTestTemplate;
import org.sangyunpark99.post.application.dto.CreatePostRequestDto;
import org.sangyunpark99.post.domain.content.PostState;
import org.sangyunpark99.post.ui.dto.GetPostContentResponseDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.sangyunpark99.acceptance.steps.FeedAcceptanceSteps.*;

class FeedAcceptanceTest extends AcceptanceTestTemplate {

    /**
     * User1 --- follow ---> User2
     * User1 --- follow ----> User3
     * **/

    @BeforeEach
    void setUp() {
        super.init();
    }

    /**
     * User 2 create Post 1
     * User 1 Get Post 1 From Feed
     * Because User 1 follow User2
     * **/
    @Test
    void givenUserHasFollowerAndCreatePost_WhenFollowerUserRequestFeed_thenFollowerCanGetPostFromFeed() {
        //given
        CreatePostRequestDto dto = new CreatePostRequestDto(2L, "user 1 can get this post", PostState.PUBLIC);
        Long createPostId = requestCreatePost(dto);

        //when, 팔로워 피드 요청
        List<GetPostContentResponseDto> result = requestFeed(1L);

        //then
        assertEquals(1, result.size());
        assertEquals(createPostId, result.get(0).getId());
    }
}
