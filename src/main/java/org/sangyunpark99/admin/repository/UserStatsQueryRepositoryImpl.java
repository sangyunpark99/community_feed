package org.sangyunpark99.admin.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.sangyunpark99.admin.ui.dto.users.GetDailyRegisterUserResponseDto;
import org.sangyunpark99.admin.ui.query.UserStatsQueryRepository;
import org.sangyunpark99.common.utils.TimeCalculator;
import org.sangyunpark99.user.repository.entity.QUserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserStatsQueryRepositoryImpl implements UserStatsQueryRepository {

    private final JPAQueryFactory queryFactory;
    private static final QUserEntity userEntity = QUserEntity.userEntity;


    @Override
    public List<GetDailyRegisterUserResponseDto> getDailyRegisterUserStats(int beforeDays) {
        return queryFactory
                .select(
                        Projections.fields(
                                GetDailyRegisterUserResponseDto.class,
                                userEntity.regDt.as("date"),
                                userEntity.count().as("count")
                        )
                ).from(userEntity)
                .where(userEntity.regDt.after(TimeCalculator.getDateDaysAgo(beforeDays)))
                .groupBy(userEntity.regDt)
                .orderBy(userEntity.regDt.asc())
                .fetch();
    }
}
