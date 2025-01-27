package org.sangyunpark99.post.repository.post_queue;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.sangyunpark99.post.repository.entity.like.QLikeEntity;
import org.sangyunpark99.post.repository.entity.post.QPostEntity;
import org.sangyunpark99.post.repository.entity.post.QUserPostQueueEntity;
import org.sangyunpark99.post.ui.dto.GetPostContentResponseDto;
import org.sangyunpark99.user.repository.entity.QUserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserPostQueueQueryRepositoryImpl implements UserPostQueueQueryRepository{

    private final JPAQueryFactory queryFactory;

    private static final QUserPostQueueEntity userPostQueueEntity = QUserPostQueueEntity.userPostQueueEntity;
    private static final QPostEntity postEntity = QPostEntity.postEntity;
    private static final QUserEntity userEntity = QUserEntity.userEntity;
    private static final QLikeEntity likeEntity = QLikeEntity.likeEntity;

    @Override
    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastContentId) {
        return queryFactory
                .select(
                        Projections.fields(
                                GetPostContentResponseDto.class,
                                postEntity.id.as("id"),
                                postEntity.content.as("content"),
                                userEntity.id.as("userId"),
                                userEntity.name.as("userName"),
                                userEntity.profileImageUrl.as("userProfileImage"),
                                postEntity.regDate.as("createdAt"),
                                postEntity.modDate.as("updatedAt"),
                                postEntity.commentCount.as("commentCount"),
                                postEntity.likeCount.as("likeCount"),
                                likeEntity.isNotNull().as("isLikedByMe")
                        )
                )
                .from(userPostQueueEntity)
                .join(postEntity).on(userPostQueueEntity.postId.eq(postEntity.id))
                .join(userEntity).on(userPostQueueEntity.authorId.eq(userEntity.id))
                .leftJoin(likeEntity).on(hasLike(userId))
                .where(
                        userPostQueueEntity.userId.eq(userId),
                        hasLastDate(lastContentId)
                )
                .orderBy(userPostQueueEntity.postId.desc())
                .limit(20)
                .fetch();
    }

    private BooleanExpression hasLastDate(Long lastId) {
        if(lastId == null) {
            return null;
        }

        return postEntity.id.lt(lastId);
    }

    private BooleanExpression hasLike(Long userId) {
        if(userId == null) {
            return null;
        }

        return postEntity.id
                .eq(likeEntity.id.targetId)
                .and(likeEntity.id.targetType.eq("POST"))
                .and(likeEntity.id.userId.eq(userId));
    }
}
