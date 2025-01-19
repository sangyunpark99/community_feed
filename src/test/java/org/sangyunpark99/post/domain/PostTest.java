package org.sangyunpark99.post.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sangyunpark99.post.domain.content.PostContent;
import org.sangyunpark99.post.domain.content.PostState;
import org.sangyunpark99.user.domain.User;
import org.sangyunpark99.user.domain.UserInfo;

class PostTest {

    private final UserInfo info = new UserInfo("test","");
    private final User user = new User(1L, info);
    private final User otherUser = new User(2L, info);
    private final Post post = new Post(1L, user, new PostContent("content"));

    @Test
    @DisplayName("givenPostCreatedWhenLikeThenLikeCountShouldBe1")
    void givenPostCreated_whenLike_thenLikeCountShouldBe1() throws Exception{
        //when
        post.like(otherUser);

        //then
        Assertions.assertEquals(1, post.getLikeCount());
    }

    @Test
    @DisplayName("givenPostCreated_whenLikeByAuthor_thenThrowException")
    void givenPostCreated_whenLikeByAuthor_thenThrowException(){
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> post.like(user));
    }

    @Test
    @DisplayName("givenPostCreatedAndLike_whenUnLike_thenLikeCountShouldBe0")
    void givenPostCreated_whenUnLike_thenLikeCountShouldBe0(){
        //given
        post.like(otherUser);

        //when
        post.unlike();

        //then
        Assertions.assertEquals(0, post.getLikeCount());
    }

    @Test
    @DisplayName("givenPostCreated_whenUnLike_thenThrowException")
    void givenPostCreated_whenUnLike_thenThrowException(){
        //when, then
        Assertions.assertThrows(IllegalArgumentException.class, () -> post.unlike());
    }

    @Test
    @DisplayName("givenPostCreated_whenUpdateContent_thenContentShouldBeUpdated")
    void givenPostCreated_whenUpdateContent_thenContentShouldBeUpdated() throws Exception{
        //given
        String newPostContent = "new content";

        //when
        post.updatePost(user, newPostContent, PostState.PUBLIC);

        //then
        Assertions.assertEquals(newPostContent, post.getContentText());
    }

    @Test
    @DisplayName("")
    void givenPostCreated_whenUpdateContentOtherUser_thenThrowException() throws Exception{
        //given
        String newPostContent = "new content";

        //when, then
        Assertions.assertThrows(IllegalArgumentException.class, () -> post.updatePost(otherUser, newPostContent,
                PostState.PUBLIC));
    }
}
