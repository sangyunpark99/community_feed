package org.sangyunpark99.post.repository.post_queue;

import org.sangyunpark99.post.repository.entity.post.PostEntity;

import java.util.List;

public interface UserQueueRedisRepository {
    void publishPostToFollowerUsers(PostEntity postEntity, List<Long> followerIds);
    void publishPostListToFollowerUsers(List<PostEntity> postEntities, Long userId);
    void deleteFeed(Long userId, Long authorId);
}
