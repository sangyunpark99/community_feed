package org.sangyunpark99.admin.ui.dto.posts;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sangyunpark99.common.domain.Pageable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetPostTableRequestDto extends Pageable {

    private Long postId;


}
