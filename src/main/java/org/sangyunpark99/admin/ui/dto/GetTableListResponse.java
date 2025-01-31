package org.sangyunpark99.admin.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetTableListResponse<T> {

    private int totalCount;
    private List<T> tableData = new ArrayList<>();
}
