package org.sangyunpark99.post.repository.jpa;

import org.sangyunpark99.post.repository.entity.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaCommentRepository extends JpaRepository<CommentEntity, Long> {

    @Modifying
    @Query(value = "UPDATE CommentEntity c " +
            "SET c.content = :#{#comment.getContent()}, " +
            "c.modDate = now() WHERE c.id = :#{#comment.getId()}")
    void updateCommentEntity(CommentEntity comment);

    @Modifying
    @Query(value = "UPDATE CommentEntity c "
            +"SET c.likeCount = c.likeCount + :likeCount,"
            +"c.modDate = now() "
            +"WHERE c.id = :commentId")
    void updateCommentLikeCount(Long commentId, Integer likeCount);
}
