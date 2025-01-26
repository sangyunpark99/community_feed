package org.sangyunpark99.post.repository.post_queue;

import org.sangyunpark99.post.repository.entity.post.PostEntity;

public interface UserPostQueueCommentRepository {

    void publishPost(PostEntity postEntity);

    void saveFollowPost(Long userId, Long targetId);

    void deleteUnfollowPost(Long userid, Long targetId);
}
