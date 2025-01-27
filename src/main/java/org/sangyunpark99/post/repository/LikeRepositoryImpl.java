package org.sangyunpark99.post.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.sangyunpark99.post.application.interfaces.LikeRepository;
import org.sangyunpark99.post.domain.Post;
import org.sangyunpark99.post.domain.comment.Comment;
import org.sangyunpark99.post.repository.entity.comment.CommentEntity;
import org.sangyunpark99.post.repository.entity.like.LikeEntity;
import org.sangyunpark99.post.repository.jpa.JpaCommentRepository;
import org.sangyunpark99.post.repository.jpa.JpaLikeRepository;
import org.sangyunpark99.post.repository.jpa.JpaPostRepository;
import org.sangyunpark99.user.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    private final JpaPostRepository jpaPostRepository;
    private final JpaCommentRepository jpaCommentRepository;
    private final JpaLikeRepository jpaLikeRepository;

    @Override
    public boolean checkLike(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        return jpaLikeRepository.existsById(likeEntity.getId());
    }

    @Override
    public boolean checkLike(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        return jpaLikeRepository.existsById(likeEntity.getId());
    }

    @Override
    @Transactional
    public void like(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        entityManager.persist(likeEntity);
        jpaPostRepository.updatePostLikeCount(post.getId(), 1);
    }

    @Override
    @Transactional
    public void unlike(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        jpaLikeRepository.deleteById(likeEntity.getId());
        jpaPostRepository.updatePostLikeCount(post.getId(), -1);
    }

    @Override
    @Transactional
    public void like(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        entityManager.persist(likeEntity);
        jpaCommentRepository.updateCommentLikeCount(comment.getId(), 1);
    }

    @Override
    @Transactional
    public void unlike(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        jpaLikeRepository.deleteById(likeEntity.getId());
        jpaCommentRepository.updateCommentLikeCount(comment.getId(), -1);
    }
}
