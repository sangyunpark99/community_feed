package org.sangyunpark99.post.application;

import org.junit.jupiter.api.Test;
import org.sangyunpark99.post.application.dto.LikePostRequestDto;
import org.sangyunpark99.post.application.dto.UpdatePostRequestDto;
import org.sangyunpark99.post.domain.Post;
import org.sangyunpark99.post.domain.content.PostState;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PostServiceTest extends PostApplicationTestTemplate{

    @Test
    void givenPostDto_whenCreatePost_thenReturnPost() {
        // when
        Post savedPost = postService.create(postRequestDto);

        // then
        Post foundPost = postService.get(savedPost.getId());
        assertEquals(foundPost, savedPost);
    }

    @Test
    void givenPostDto_whenUpdatePost_thenReturnUpdatedPost() {
        // given
        Post savedPost = postService.create(postRequestDto);
        UpdatePostRequestDto updateDto = new UpdatePostRequestDto(savedPost.getId(), user.getId(),
                "this is updated content", PostState.PRIVATE);

        // when
        Post updatedPost = postService.update(updateDto);

        // then
        assertEquals(savedPost.getId(), updatedPost.getId());
        assertEquals(savedPost.getAuthor(), updatedPost.getAuthor());
        assertEquals(savedPost.getContent(), updatedPost.getContent());
        assertEquals(savedPost.getState(), updatedPost.getState());
    }

    @Test
    void givenCreatedPost_whenLikePost_thenPostLiked() {
        // given
        Post savedPost = postService.create(postRequestDto);

        // when
        LikePostRequestDto likeDto = new LikePostRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeDto);

        // then
        Post likedPost = postService.get(savedPost.getId());
        assertEquals(1, likedPost.getLikeCount());
    }

    @Test
    void givenLikedPost_whenUnlikePost_thenPostUnliked() {
        // given
        Post savedPost = postService.create(postRequestDto);
        LikePostRequestDto likeDto = new LikePostRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeDto);

        // when
        postService.unlikePost(likeDto);

        // then
        Post unlikedPost = postService.get(savedPost.getId());
        assertEquals(0, unlikedPost.getLikeCount());
    }

    @Test
    void givenLikedPost_whenLikePost_thenPostLiked() {
        // given
        Post savedPost = postService.create(postRequestDto);
        LikePostRequestDto likeDto = new LikePostRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeDto);

        // when
        postService.likePost(likeDto);

        // then
        Post likedPost = postService.get(savedPost.getId());
        assertEquals(1, likedPost.getLikeCount());
    }

    @Test
    void givenUnlikedPost_whenUnlikePost_thenPostUnliked() {
        // given
        Post savedPost = postService.create(postRequestDto);
        LikePostRequestDto likeDto = new LikePostRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeDto);
        postService.unlikePost(likeDto);

        // when
        postService.unlikePost(likeDto);

        // then
        Post unlikedPost = postService.get(savedPost.getId());
        assertEquals(0, unlikedPost.getLikeCount());
    }


}
