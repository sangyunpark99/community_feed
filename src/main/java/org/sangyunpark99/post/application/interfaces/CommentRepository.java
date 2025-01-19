package org.sangyunpark99.post.application.interfaces;

import org.sangyunpark99.post.domain.comment.Comment;

import java.util.Optional;

public interface CommentRepository {

    Comment save(Comment comment);

    Optional<Comment> findById(Long id);
}
