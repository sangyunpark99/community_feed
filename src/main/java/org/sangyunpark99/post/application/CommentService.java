package org.sangyunpark99.post.application;

import org.sangyunpark99.post.application.dto.CreateCommentRequestDto;
import org.sangyunpark99.post.application.dto.LikeCommentRequestDtp;
import org.sangyunpark99.post.application.dto.UpdateCommentRequestDto;
import org.sangyunpark99.post.application.interfaces.CommentRepository;
import org.sangyunpark99.post.application.interfaces.LikeRepository;
import org.sangyunpark99.post.domain.Post;
import org.sangyunpark99.post.domain.comment.Comment;
import org.sangyunpark99.user.application.UserService;
import org.sangyunpark99.user.domain.User;

public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;
    private final LikeRepository likeRepository;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService,
                          LikeRepository likeRepository) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
        this.likeRepository = likeRepository;
    }

    public Comment getComments(Long id) {
        return commentRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Comment createComment(CreateCommentRequestDto dto) {
        Post post = postService.get(dto.postId());
        User user = userService.getUser(dto.userId());
        Comment comment = Comment.createComment(post, user, dto.content());
       return commentRepository.save(comment);
    }

    public Comment updateComment(UpdateCommentRequestDto dto) {
        User user = userService.getUser(dto.userId());
        Comment comment = commentRepository.findById(dto.commentId()).orElseThrow(IllegalArgumentException::new);
        comment.updateComment(user,dto.content());

        return commentRepository.save(comment);
    }

    public void likeComment(LikeCommentRequestDtp dto) {
        Comment comment = commentRepository.findById(dto.commentId()).orElseThrow(IllegalArgumentException::new);
        User user = userService.getUser(dto.userId());

        if(likeRepository.checkLike(comment, user)) {
            return;
        }

        comment.like(user);
        likeRepository.like(comment, user);
    }

    public void unlikeComment(LikeCommentRequestDtp dto) {
        Comment comment = commentRepository.findById(dto.commentId()).orElseThrow(IllegalArgumentException::new);
        User user = userService.getUser(dto.userId());

        if(!likeRepository.checkLike(comment, user)) {
            return;
        }

        comment.unlike();
        likeRepository.unlike(comment, user);
    }
}
