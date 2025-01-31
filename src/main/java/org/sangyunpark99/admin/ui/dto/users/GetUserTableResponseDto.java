package org.sangyunpark99.admin.ui.dto.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sangyunpark99.common.TimeCalculator;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserTableResponseDto {

    @Getter
    private Long id;

    @Getter
    private String email;

    @Getter
    private String name;

    @Getter
    private String role;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLoginAt;

    public String getCreatedAt() {
        return TimeCalculator.getFormattedDate(createdAt);
    }

    public String getUpdatedAt() {
        return TimeCalculator.getFormattedDate(updatedAt);
    }

    public String lastLoginAt() {
        return TimeCalculator.getFormattedDate(lastLoginAt);
    }
}
