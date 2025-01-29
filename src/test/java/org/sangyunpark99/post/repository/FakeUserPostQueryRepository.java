package org.sangyunpark99.post.repository;

import org.sangyunpark99.post.repository.entity.post.PostEntity;
import org.sangyunpark99.post.repository.post_queue.UserPostQueueQueryRepository;
import org.sangyunpark99.post.ui.dto.GetPostContentResponseDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("test")
public class FakeUserPostQueryRepository implements UserPostQueueQueryRepository {

    private FakeUserQueueRedisRepository fakeUserQueueRedisRepository;

    public FakeUserPostQueryRepository(FakeUserQueueRedisRepository fakeUserQueueRedisRepository) {
        this.fakeUserQueueRedisRepository = fakeUserQueueRedisRepository;
    }

    @Override
    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId) {

        List<PostEntity> postEntities = fakeUserQueueRedisRepository.getPostsByUserId(userId);
        List<GetPostContentResponseDto> result = new ArrayList<>();

        for(PostEntity postEntity: postEntities) {
            GetPostContentResponseDto res = GetPostContentResponseDto.builder()
                    .id(postEntity.getId())
                    .build();

            result.add(res);
        }
        
        return result;
    }
}
