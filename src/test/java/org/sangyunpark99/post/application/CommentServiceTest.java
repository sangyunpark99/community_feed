package org.sangyunpark99.post.application;

import org.junit.jupiter.api.Test;
import org.sangyunpark99.post.application.dto.LikeCommentRequestDtp;
import org.sangyunpark99.post.application.dto.UpdateCommentRequestDto;
import org.sangyunpark99.post.domain.comment.Comment;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommentServiceTest extends PostApplicationTestTemplate {

    @Test
    void givenCommentDto_whenCreateComment_thenReturnComment() {
        //given
        postService.create(postRequestDto);

        // when
        Comment savedComment = commentService.createComment(commentRequestDto);

        // then
        Comment foundComment = commentService.getComments(savedComment.getId());
        assertEquals(foundComment, savedComment);
    }


    @Test
    void givenCommentDto_whenUpdateComment_thenReturnUpdatedComment() {
        //given
        postService.create(postRequestDto);
        Comment savedComment = commentService.createComment(commentRequestDto);
        String updatedContent = "this is updated content";

        // when
        Comment updatedComment = commentService.updateComment(new UpdateCommentRequestDto(savedComment.getId(), user.getId(), updatedContent));

        // then
        assertEquals(savedComment.getId(), updatedComment.getId());
        assertEquals(savedComment.getAuthor(), updatedComment.getAuthor());
        assertEquals(updatedContent, updatedComment.getContent());
    }

    @Test
    void givenCreatedComment_whenLikeComment_thenCommentLiked() {
        //given
        postService.create(postRequestDto);
        Comment savedComment = commentService.createComment(commentRequestDto);

        // when
        commentService.likeComment(new LikeCommentRequestDtp(savedComment.getId(), otherUser.getId()));

        // then
        Comment likedComment = commentService.getComments(savedComment.getId());
        assertEquals(1, likedComment.getLikeCount());
    }

    @Test
    void givenLikedComment_whenUnlikeComment_thenCommentUnliked() {
        //given
        postService.create(postRequestDto);
        Comment savedComment = commentService.createComment(commentRequestDto);
        commentService.likeComment(new LikeCommentRequestDtp(savedComment.getId(), otherUser.getId()));

        // when
        commentService.unlikeComment(new LikeCommentRequestDtp(savedComment.getId(), otherUser.getId()));

        // then
        Comment unlikedComment = commentService.getComments(savedComment.getId());
        assertEquals(0, unlikedComment.getLikeCount());
    }

    @Test
    void givenLikedComment_whenLikeComment_thenCommentLiked() {
        //given
        postService.create(postRequestDto);
        Comment savedComment = commentService.createComment(commentRequestDto);
        commentService.likeComment(new LikeCommentRequestDtp(savedComment.getId(), otherUser.getId()));

        // when
        commentService.likeComment(new LikeCommentRequestDtp(savedComment.getId(), otherUser.getId()));

        // then
        Comment likedComment = commentService.getComments(savedComment.getId());
        assertEquals(1, likedComment.getLikeCount());
    }

    @Test
    void givenUnLikedComment_whenUnlikeComment_thenCommentUnliked() {
        //given
        postService.create(postRequestDto);
        Comment savedComment = commentService.createComment(commentRequestDto);

        // when
        commentService.unlikeComment(new LikeCommentRequestDtp(savedComment.getId(), otherUser.getId()));

        // then
        Comment unlikedComment = commentService.getComments(savedComment.getId());
        assertEquals(0, unlikedComment.getLikeCount());
    }
}