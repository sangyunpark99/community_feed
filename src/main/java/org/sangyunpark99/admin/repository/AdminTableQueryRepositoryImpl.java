package org.sangyunpark99.admin.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.sangyunpark99.admin.ui.dto.GetTableListResponse;
import org.sangyunpark99.admin.ui.dto.posts.GetPostTableRequestDto;
import org.sangyunpark99.admin.ui.dto.posts.GetPostTableResponseDto;
import org.sangyunpark99.admin.ui.dto.users.GetUserTableRequestDto;
import org.sangyunpark99.admin.ui.dto.users.GetUserTableResponseDto;
import org.sangyunpark99.admin.ui.query.AdminTableQueryRepository;
import org.sangyunpark99.auth.repository.entity.QUserAuthEntity;
import org.sangyunpark99.post.repository.entity.post.QPostEntity;
import org.sangyunpark99.user.repository.entity.QUserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminTableQueryRepositoryImpl implements AdminTableQueryRepository {

    private final JPAQueryFactory queryFactory;
    private static final QUserAuthEntity userAuthEntity = QUserAuthEntity.userAuthEntity;
    private static final QUserEntity userEntity = QUserEntity.userEntity;
    private static final QPostEntity postEntity = QPostEntity.postEntity;

    @Override
    public GetTableListResponse<GetUserTableResponseDto> getUserTableData(GetUserTableRequestDto dto) {

        int total = queryFactory.select(userEntity.id)
                .from(userEntity)
                .where(likeName(dto.getName()))
                .fetch()
                .size();

        List<Long> Ids = queryFactory
                .select(userEntity.id)
                .from(userEntity)
                .where(
                        likeName(dto.getName())
                ).orderBy(userEntity.id.desc())
                .offset(dto.getOffset())
                .limit(dto.getLimit())
                .fetch();

        // 커버링 인덱스로 기존에 50만개의 데이터를 모두 가져오는 것에 비해, 아이디만 빠르게 가져오기 때문에 데이터가 많아지는 것에 대한 부하가 적습니다.
        // id는 인덱싱이 자동으로 되어 있습니다.

        // 가져온 id를 실제 테이블의 클러스터 인덱스에 접근을 해서 데이터를 가져오도록 합니다.

        List<GetUserTableResponseDto> result = queryFactory
                .select(
                        Projections.fields(
                               GetUserTableResponseDto.class,
                               userEntity.id.as("id"),
                               userAuthEntity.email.as("email"),
                               userEntity.name.as("name"),
                               userAuthEntity.role.as("role"),
                               userEntity.regDate.as("createdAt"),
                               userEntity.modDate.as("updatedAt"),
                               userAuthEntity.lastLoginDt.as("lastLoginAt")
                        )
                ).from(userEntity)
                .join(userAuthEntity).on(userAuthEntity.userId.eq(userEntity.id))
                .where(
                        userEntity.id.in(Ids)
                ).orderBy(userEntity.id.desc())
                .fetch();

        return new GetTableListResponse<>(total, result);
    }

    @Override
    public GetTableListResponse<GetPostTableResponseDto> getPostTableData(GetPostTableRequestDto dto) {

        int total = queryFactory.select(postEntity.id)
                .from(postEntity)
                .where(eqPostId(dto.getPostId()))
                .fetch()
                .size();

        List<Long> ids = queryFactory.select(postEntity.id)
                .from(postEntity)
                .where(
                        eqPostId(dto.getPostId())
                )
                .orderBy(postEntity.id.desc())
                .offset(dto.getOffset())
                .limit(dto.getLimit())
                .fetch();

        List<GetPostTableResponseDto> result = queryFactory
                .select(
                        Projections.fields(
                                GetPostTableResponseDto.class,
                                postEntity.id.as("postId"),
                                userEntity.id.as("userId"),
                                userEntity.name.as("userName"),
                                postEntity.content.as("content"),
                                postEntity.regDate.as("createdAt"),
                                postEntity.modDate.as("updatedAt")
                        )
                )
                .from(postEntity)
                .join(userEntity).on(userEntity.id.eq(postEntity.author.id))
                .where(postEntity.id.in(ids))
                .orderBy(postEntity.id.desc())
                .fetch();

        return new GetTableListResponse<>(total, result);
    }

    private BooleanExpression likeName(String name) {
        if(name == null || name.isBlank()) {
            return null;
        }

        return userEntity.name.like(name + "%"); // 'name%' 패턴과 일치하는 조건 생성
    }

    private BooleanExpression eqPostId(Long id) {
        if(id == null) {
            return null;
        }

        return postEntity.id.eq(id);
    }
}
