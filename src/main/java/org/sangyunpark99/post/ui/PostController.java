package org.sangyunpark99.post.ui;

import lombok.RequiredArgsConstructor;
import org.sangyunpark99.common.idempotency.Idempotent;
import org.sangyunpark99.common.ui.Response;
import org.sangyunpark99.post.application.PostService;
import org.sangyunpark99.post.application.dto.CreatePostRequestDto;
import org.sangyunpark99.post.application.dto.LikePostRequestDto;
import org.sangyunpark99.post.application.dto.UpdatePostRequestDto;
import org.sangyunpark99.post.domain.Post;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    public Response<Long> createPost(@RequestBody CreatePostRequestDto dto) {
        Post post = postService.createPost(dto);
        return Response.ok(post.getId());
    }

    @PostMapping("/{postId}")
    public Response<Long> updatePost(@PathVariable(name = "postId") Long postId, @RequestBody UpdatePostRequestDto dto) {
        Post post = postService.updatePost(postId, dto);
        return Response.ok(post.getId());
    }

    @Idempotent
    @PostMapping("/like")
    public Response<Void> likePost(@RequestBody LikePostRequestDto dto) {
        postService.likePost(dto);
        return Response.ok(null);
    }

    @PostMapping("/unlike")
    public Response<Void> unlikePost(@RequestBody LikePostRequestDto dto) {
        postService.unlikePost(dto);
        return Response.ok(null);
    }
}
