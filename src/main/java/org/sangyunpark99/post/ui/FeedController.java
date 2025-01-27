package org.sangyunpark99.post.ui;

import lombok.RequiredArgsConstructor;
import org.sangyunpark99.common.ui.Response;
import org.sangyunpark99.post.repository.post_queue.UserPostQueueQueryRepository;
import org.sangyunpark99.post.ui.dto.GetPostContentResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {

    private final UserPostQueueQueryRepository userPostQueueQueryRepository;

    @GetMapping("/{userId}")
    public Response<List<GetPostContentResponseDto>> getPostContent(@PathVariable(name = "userId") Long userId,
                                                                    Long lastPostId) {
        List<GetPostContentResponseDto> contentResponse = userPostQueueQueryRepository.getContentResponse(userId, lastPostId);
        return Response.ok(contentResponse);
    }

}
