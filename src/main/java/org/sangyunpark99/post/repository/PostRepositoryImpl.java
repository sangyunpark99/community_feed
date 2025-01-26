package org.sangyunpark99.post.repository;

import lombok.RequiredArgsConstructor;
import org.sangyunpark99.post.application.interfaces.PostRepository;
import org.sangyunpark99.post.domain.Post;
import org.sangyunpark99.post.repository.entity.post.PostEntity;
import org.sangyunpark99.post.repository.jpa.JpaPostRepository;
import org.sangyunpark99.post.repository.post_queue.UserPostQueueCommentRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final JpaPostRepository jpaPostRepository;
    private final UserPostQueueCommentRepository userPostQueueCommentRepository;

    @Override
    public Post save(Post post) {
        PostEntity postEntity = new PostEntity(post);
        if(post.getId() != null) {
            jpaPostRepository.updatePostEntity(postEntity);
            return post;
        }
        postEntity = jpaPostRepository.save(postEntity);
        userPostQueueCommentRepository.publishPost(postEntity);
        return postEntity.toPost();
    }

    @Override
    public Post findById(Long id) {
        PostEntity postEntity = jpaPostRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return postEntity.toPost();
    }
}