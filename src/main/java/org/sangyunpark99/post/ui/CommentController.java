package org.sangyunpark99.post.ui;

import lombok.RequiredArgsConstructor;
import org.sangyunpark99.common.ui.Response;
import org.sangyunpark99.post.application.CommentService;
import org.sangyunpark99.post.application.dto.CreateCommentRequestDto;
import org.sangyunpark99.post.application.dto.LikeCommentRequestDto;
import org.sangyunpark99.post.application.dto.UpdateCommentRequestDto;
import org.sangyunpark99.post.domain.comment.Comment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public Response<Long> createComment(@RequestBody CreateCommentRequestDto dto) {
        Comment comment = commentService.createComment(dto);
        return Response.ok(comment.getId());
    }

    @PostMapping("/{commentId}")
    public Response<Long> updateComment(@PathVariable(name = "commentId") Long commentId,
                                        @RequestBody UpdateCommentRequestDto dto) {
        Comment comment = commentService.updateComment(commentId, dto);
        return Response.ok(comment.getId());
    }

    @PostMapping("/like")
    public Response<Void> likeComment(@RequestBody LikeCommentRequestDto dto) {
        commentService.likeComment(dto);
        return Response.ok(null);
    }

    @PostMapping("/unlike")
    public Response<Void> unlikeComment(@RequestBody LikeCommentRequestDto dto) {
        commentService.unlikeComment(dto);
        return Response.ok(null);
    }
}
