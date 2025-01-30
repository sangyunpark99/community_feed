package org.sangyunpark99.admin.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetDailyRegisterUserResponseDto {
    private LocalDate date;
    private Long count;
}
