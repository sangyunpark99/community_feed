package org.sangyunpark99.post.application.interfaces;

import org.sangyunpark99.post.domain.comment.Comment;

public interface CommentRepository {

    Comment save(Comment comment);

    Comment findById(Long id);
}
