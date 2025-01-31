package org.sangyunpark99.post.repository;

import org.sangyunpark99.post.repository.entity.post.PostEntity;
import org.sangyunpark99.post.repository.post_queue.UserQueueRedisRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile({"!test"})
public class UserQueueRedisRepositoryImpl implements UserQueueRedisRepository {
    @Override
    public void publishPostToFollowerUsers(PostEntity postEntity, List<Long> followerIds) {

    }

    @Override
    public void publishPostListToFollowerUsers(List<PostEntity> postEntities, Long userId) {

    }

    @Override
    public void deleteFeed(Long userId, Long authorId) {

    }
}
