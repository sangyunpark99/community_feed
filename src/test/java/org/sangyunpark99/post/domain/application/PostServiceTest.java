package org.sangyunpark99.post.domain.application;

import org.junit.jupiter.api.Test;
import org.sangyunpark99.fake.FakeObjectFactory;
import org.sangyunpark99.post.application.PostService;
import org.sangyunpark99.post.application.dto.CreatePostRequestDto;
import org.sangyunpark99.post.application.dto.LikePostRequestDto;
import org.sangyunpark99.post.application.dto.UpdatePostRequestDto;
import org.sangyunpark99.post.domain.Post;
import org.sangyunpark99.post.domain.content.PostState;
import org.sangyunpark99.user.application.UserService;
import org.sangyunpark99.user.application.dto.CreateUserRequestDto;
import org.sangyunpark99.user.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostServiceTest {

    private final UserService userService = FakeObjectFactory.getUserService();
    private final PostService postService = FakeObjectFactory.getPostService();

    private final User user = userService.createUser(new CreateUserRequestDto("user1", ""));
    private final User otherUser = userService.createUser(new CreateUserRequestDto("user2", ""));

    private final CreatePostRequestDto dto = new CreatePostRequestDto(user.getId(), "this is test content",
            PostState.PUBLIC);

    @Test
    void givenPostDto_whenCreatePost_thenReturnPost() {
        // when
        Post savedPost = postService.create(dto);

        // then
        Post foundPost = postService.get(savedPost.getId());
        assertEquals(foundPost, savedPost);
    }

    @Test
    void givenPostDto_whenUpdatePost_thenReturnUpdatedPost() {
        // given
        Post savedPost = postService.create(dto);
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
        Post savedPost = postService.create(dto);

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
        Post savedPost = postService.create(dto);
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
        Post savedPost = postService.create(dto);
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
        Post savedPost = postService.create(dto);
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
