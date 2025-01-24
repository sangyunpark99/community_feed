package org.sangyunpark99.user.repository.jpa;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.sangyunpark99.user.application.dto.GetUserListResponseDto;
import org.sangyunpark99.user.repository.entity.QUserEntity;
import org.sangyunpark99.user.repository.entity.QUserRelationshipEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaUserListPagingQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private static final QUserEntity user = QUserEntity.userEntity;
    private static final QUserRelationshipEntity relation = QUserRelationshipEntity.userRelationshipEntity;

    public List<GetUserListResponseDto> getFollwerList(Long userId, Long lastFollowerId) {
        return jpaQueryFactory
                .select(
                        Projections.fields(
                                GetUserListResponseDto.class
                        )
                )
                .from(relation)
                .join(user).on(relation.followerUserId.eq(user.id))
                .where(
                        relation.followingUserId.eq(userId),
                        hasLastData(lastFollowerId)
                )
                .orderBy(user.id.desc())
                .limit(20)
                .fetch();

    }


    private BooleanExpression hasLastData(Long lastId) {
        if(lastId == null) {
            return null;
        }

        return user.id.lt(lastId);
    }
}
