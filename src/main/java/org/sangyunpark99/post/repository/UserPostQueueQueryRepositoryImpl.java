package org.sangyunpark99.post.repository;

import org.sangyunpark99.post.repository.post_queue.UserPostQueueQueryRepository;
import org.sangyunpark99.post.ui.dto.GetPostContentResponseDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile({"!test"})
public class UserPostQueueQueryRepositoryImpl implements UserPostQueueQueryRepository {
    @Override
    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId) {
        return null;
    }
}
