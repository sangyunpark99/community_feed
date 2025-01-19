package org.sangyunpark99.post.domain.comment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.sangyunpark99.post.domain.Post;
import org.sangyunpark99.post.domain.content.CommentContent;
import org.sangyunpark99.post.domain.content.PostContent;
import org.sangyunpark99.user.domain.User;
import org.sangyunpark99.user.domain.UserInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommentTest {

    private final UserInfo info = new UserInfo("test","");
    private final User user = new User(1L, info);
    private final User otherUser = new User(2L, info);
    private final Post post = new Post(1L, user, new PostContent("content"));
    private final Comment comment = new Comment(1L, new CommentContent("comment"), post, user);

    @Test
    void givenCommentCreated_whenLike_thenLikeCountShouldBe1(){
        //when
        comment.like(otherUser);

        //then
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenCommentCreated_whenLikeByAuthor_thenThrowException(){
        //then
        assertThrows(IllegalArgumentException.class, () -> comment.like(user));
    }

    @Test
    void givenCommentCreated_whenUnLike_thenLikeCountShouldBe0(){
        //given
        comment.like(otherUser);

        //when
        comment.unlike();

        //then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenCommentCreated_whenUnLike_thenThrowException(){
        //when, then
        assertThrows(IllegalArgumentException.class, () -> comment.unlike());
    }

    @Test
    void givenCommentCreated_whenUpdateContent_thenContentShouldBeUpdated(){
        //given
        String newCommentContent = "new comment";

        //when
        comment.updateComment(user, newCommentContent);

        //then
        assertEquals(newCommentContent, comment.getContent());
    }

    @Test
    void givenCommentCreated_whenUpdateContentByOtherUser_thenThrowException(){
        //given
        String newCommentContent = "new comment";

        //when, then
        assertThrows(IllegalArgumentException.class, () -> comment.updateComment(otherUser, newCommentContent));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenCommentCreated_whenUpdateContentByNull_thenThrowException(String content){
        //when, then
        assertThrows(IllegalArgumentException.class, () -> comment.updateComment(null,content));
    }

    @Test
    void givenComment_whenUpdateContentOver100_thenThrowException(){
        //given
        String newCommentContent = "a".repeat(101);

        //when, then
        assertThrows(IllegalArgumentException.class, () -> comment.updateComment(user, newCommentContent));
    }
}
