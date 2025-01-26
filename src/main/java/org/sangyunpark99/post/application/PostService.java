package org.sangyunpark99.post.application;

import lombok.RequiredArgsConstructor;
import org.sangyunpark99.post.application.dto.CreatePostRequestDto;
import org.sangyunpark99.post.application.dto.LikePostRequestDto;
import org.sangyunpark99.post.application.dto.UpdatePostRequestDto;
import org.sangyunpark99.post.application.interfaces.LikeRepository;
import org.sangyunpark99.post.application.interfaces.PostRepository;
import org.sangyunpark99.post.domain.Post;
import org.sangyunpark99.user.application.UserService;
import org.sangyunpark99.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository; // interface에 의존
    private final LikeRepository likeRepository;
    private final UserService userService;

    public Post getPost(final Long id) {
        return postRepository.findById(id);
    }

    @Transactional
    public Post createPost(CreatePostRequestDto dto) {
        User user = userService.getUser(dto.userId());
        Post post = Post.createPost(null, user, dto.content() ,dto.state());
        return postRepository.save(post);
    }

    @Transactional
    public Post updatePost(Long id, UpdatePostRequestDto dto) {
        Post post = getPost(id);
        User user = userService.getUser(dto.userId());

        post.updatePost(user, dto.content(), dto.state());
        return postRepository.save(post);
    }

    @Transactional
    public void likePost(LikePostRequestDto dto) {
        Post post = getPost(dto.postId());
        User user = userService.getUser(dto.userId());

        if(likeRepository.checkLike(post, user)) {
            return;
        }

        post.like(user);
        likeRepository.like(post, user);
    }

    @Transactional
    public void unlikePost(LikePostRequestDto dto) {
        Post post = getPost(dto.postId());
        User user = userService.getUser(dto.userId());

        if(!likeRepository.checkLike(post, user)) {
            return;
        }

        post.unlike();
        likeRepository.unlike(post, user);
    }
}
