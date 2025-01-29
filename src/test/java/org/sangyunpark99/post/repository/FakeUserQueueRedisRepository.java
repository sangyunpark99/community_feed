package org.sangyunpark99.post.repository;

import org.sangyunpark99.post.repository.entity.post.PostEntity;
import org.sangyunpark99.post.repository.post_queue.UserQueueRedisRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Profile("test")
public class FakeUserQueueRedisRepository implements UserQueueRedisRepository {

    private final Map<Long, Set<PostEntity>> queue = new HashMap<>();

    @Override
    public void publishPostToFollowerUsers(PostEntity postEntity, List<Long> followerIds) {
        for(Long userId: followerIds) {
                if(queue.containsKey(userId)) {
                    queue.get(userId).add(postEntity);
                } else {
                    queue.put(userId, new HashSet<>(List.of(postEntity)));
                }
        }
    }

    @Override
    public void publishPostListToFollowerUsers(List<PostEntity> postEntities, Long userId) {
        if(queue.containsKey(userId)) {
            queue.get(userId).addAll(postEntities);
        } else {
            queue.put(userId, new HashSet<>(postEntities));
        }
    }

    @Override
    public void deleteFeed(Long userId, Long authorId) {
        if(queue.containsKey(userId)) {
            queue.get(userId).removeIf(post -> post.getAuthor().getId().equals(authorId));
        }
    }

    public List<PostEntity> getPostsByUserId(Long userId) {
        return List.copyOf(queue.get(userId));
    }
}
