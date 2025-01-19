package org.sangyunpark99.post.application;

import org.sangyunpark99.post.application.dto.CreatePostRequestDto;
import org.sangyunpark99.post.application.dto.LikePostRequestDto;
import org.sangyunpark99.post.application.dto.UpdatePostRequestDto;
import org.sangyunpark99.post.application.interfaces.LikeRepository;
import org.sangyunpark99.post.application.interfaces.PostRepository;
import org.sangyunpark99.post.domain.Post;
import org.sangyunpark99.user.application.UserService;
import org.sangyunpark99.user.domain.User;

public class PostService {

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    private final UserService userService;

    public PostService(PostRepository postRepository, LikeRepository likeRepository, UserService userService) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public Post get(final Long id) {
        return postRepository.findById(id).orElseThrow(() -> new ArithmeticException("Post not found"));
    }

    public Post create(CreatePostRequestDto dto) {
        User user = userService.getUser(dto.userId());
        Post post = Post.createPost(null, user, dto.content() ,dto.state());
        return postRepository.save(post);
    }

    public Post update(UpdatePostRequestDto dto) {
        Post post = get(dto.postId());
        User user = userService.getUser(dto.userId());

        post.updatePost(user, dto.content(), dto.state());
        return postRepository.save(post);
    }

    public void likePost(LikePostRequestDto dto) {
        Post post = get(dto.postId());
        User user = userService.getUser(dto.userId());

        if(likeRepository.checkLike(post, user)) {
            return;
        }

        post.like(user);
        likeRepository.like(post, user);
    }

    public void unlikePost(LikePostRequestDto dto) {
        Post post = get(dto.postId());
        User user = userService.getUser(dto.userId());

        if(!likeRepository.checkLike(post, user)) {
            return;
        }

        post.unlike();
        likeRepository.unlike(post, user);
    }
}
