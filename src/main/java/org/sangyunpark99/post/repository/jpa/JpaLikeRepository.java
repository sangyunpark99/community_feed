package org.sangyunpark99.post.repository.jpa;

import org.sangyunpark99.post.repository.entity.like.LikeEntity;
import org.sangyunpark99.post.repository.entity.like.LikeIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLikeRepository extends JpaRepository<LikeEntity, LikeIdEntity> {

}
