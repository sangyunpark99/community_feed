package org.sangyunpark99.post.repository.post_queue;

import lombok.RequiredArgsConstructor;
import org.sangyunpark99.post.repository.entity.post.PostEntity;
import org.sangyunpark99.post.repository.entity.post.UserPostQueueEntity;
import org.sangyunpark99.post.repository.jpa.JpaPostRepository;
import org.sangyunpark99.post.repository.jpa.JpaUserPostQueueRepository;
import org.sangyunpark99.user.repository.entity.UserEntity;
import org.sangyunpark99.user.repository.jpa.JpaUserRelationRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserPostQueueCommentRepositoryImpl implements UserPostQueueCommentRepository{

    private final JpaPostRepository jpaPostRepository;
    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserPostQueueRepository jpaUserPostQueueRepository;

    @Override
    @Transactional
    public void publishPost(PostEntity postEntity) {
        UserEntity userEntity = postEntity.getAuthor();
        Long authorId = userEntity.getId();
        Long postId = postEntity.getId();
        List<Long> followerIds = jpaUserRelationRepository.findFollowers(authorId);

        List<UserPostQueueEntity> userPostQueueEntities = followerIds.stream()
                .map(userId -> new UserPostQueueEntity(userId, postId, authorId)).collect(Collectors.toList());

        jpaUserPostQueueRepository.saveAll(userPostQueueEntities);
    }

    @Override
    @Transactional
    public void saveFollowPost(Long userId, Long targetId) {
        List<Long> postIds = jpaPostRepository.findAllPostIdsByAuthorId(targetId); // 팔로우 하려는 유저들의 id

        List<UserPostQueueEntity> userPostQueueEntities = postIds.stream()
                .map(postId -> new UserPostQueueEntity(userId, postId, targetId)).toList();

        jpaUserPostQueueRepository.saveAll(userPostQueueEntities);
    }

    @Override
    @Transactional
    public void deleteUnfollowPost(Long userId, Long targetId) {
        jpaUserPostQueueRepository.deleteAllByUserIdAndAuthorId(userId, targetId);
    }
}
