package org.sangyunpark99.admin.ui.dto.posts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sangyunpark99.common.utils.TimeCalculator;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetPostTableResponseDto {

    @Getter
    private Long postId;

    @Getter
    private Long userId;

    @Getter
    private String userName;

    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public String getContent() {
        if(content.length() > 10) {
            return content.substring(0,10) + "...";
        }

        return content;
    }

//    public String getUserName() {
//        return username;
//    }

    public String getCreatedAt() {
        return TimeCalculator.getFormattedDate(createdAt);
    }

    public String getUpdatedAt() {
        return TimeCalculator.getFormattedDate(updatedAt);
    }
}
