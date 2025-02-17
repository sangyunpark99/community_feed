package org.sangyunpark99.admin.ui.query;

import org.sangyunpark99.admin.ui.dto.users.GetDailyRegisterUserResponseDto;

import java.util.List;

public interface UserStatsQueryRepository {

    List<GetDailyRegisterUserResponseDto> getDailyRegisterUserStats(int beforeDays);

}
