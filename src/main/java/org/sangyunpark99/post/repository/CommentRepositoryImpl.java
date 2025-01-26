package org.sangyunpark99.post.repository;

import lombok.RequiredArgsConstructor;
import org.sangyunpark99.post.application.interfaces.CommentRepository;
import org.sangyunpark99.post.domain.comment.Comment;
import org.sangyunpark99.post.repository.entity.comment.CommentEntity;
import org.sangyunpark99.post.repository.jpa.JpaCommentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final JpaCommentRepository jpaCommentRepository;

    @Override
    public Comment save(Comment comment) {
        CommentEntity commentEntity = new CommentEntity(comment);
        if(comment.getId() != null) {
            jpaCommentRepository.updateCommentEntity(new CommentEntity(comment));
            return comment;
        }

        commentEntity = jpaCommentRepository.save(commentEntity);
        return commentEntity.toComment();
    }

    @Override
    public Comment findById(Long id) {
        CommentEntity commentEntity = jpaCommentRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return commentEntity.toComment();
    }
}
