package org.sangyunpark99.post.repository.jpa;

import org.sangyunpark99.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {

    @Query("SELECT p FROM PostEntity p WHERE p.author.id = :authorId")
    List<PostEntity> findAllPostIdsByAuthorId(Long authorId);

    @Modifying
    @Query(value = "UPDATE PostEntity p SET " +
            "p.content = :#{#postEntity.getContent()}, " +
            "p.state = :#{#postEntity.getState()}," +
            "p.modDate = now()" +
            "WHERE p.id = :#{#postEntity.getId()}")
    void updatePostEntity(PostEntity postEntity);

    @Modifying
    @Query(value = "UPDATE PostEntity p "
    +"SET p.likeCount = p.likeCount + :likeCount,"
    +"p.modDate = now() "
    +"WHERE p.id = :postId")
    void updatePostLikeCount(Long postId, Integer likeCount);

    @Modifying
    @Query(value = "UPDATE PostEntity p "
            +"SET p.commentCount = p.commentCount + 1,"
            +"p.modDate = now() "
            +"WHERE p.id = :id")
    void updatePostCommentCount(Long id); // update문 : 로우
}
