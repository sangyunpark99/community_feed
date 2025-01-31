package org.sangyunpark99.admin.ui.query;

import org.sangyunpark99.admin.ui.dto.GetTableListResponse;
import org.sangyunpark99.admin.ui.dto.users.GetUserTableRequestDto;
import org.sangyunpark99.admin.ui.dto.users.GetUserTableResponseDto;

public interface AdminTableQueryRepository {
    GetTableListResponse<GetUserTableResponseDto> getUserTableData(GetUserTableRequestDto dto);
}
