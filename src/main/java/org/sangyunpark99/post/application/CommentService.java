package org.sangyunpark99.post.application;

import org.sangyunpark99.post.application.dto.CreateCommentRequestDto;
import org.sangyunpark99.post.application.dto.LikeCommentRequestDto;
import org.sangyunpark99.post.application.dto.UpdateCommentRequestDto;
import org.sangyunpark99.post.application.interfaces.CommentRepository;
import org.sangyunpark99.post.application.interfaces.LikeRepository;
import org.sangyunpark99.post.domain.Post;
import org.sangyunpark99.post.domain.comment.Comment;
import org.sangyunpark99.user.application.UserService;
import org.sangyunpark99.user.domain.User;
import org.springframework.stereotype.Service;

@Service
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
        return commentRepository.findById(id);
    }

    public Comment createComment(CreateCommentRequestDto dto) {
        Post post = postService.getPost(dto.postId());
        User user = userService.getUser(dto.userId());
        Comment comment = Comment.createComment(post, user, dto.content());
       return commentRepository.save(comment);
    }

    public Comment updateComment(Long commentId, UpdateCommentRequestDto dto) {
        User user = userService.getUser(dto.userId());
        Comment comment = commentRepository.findById(commentId);
        comment.updateComment(user,dto.content());

        return commentRepository.save(comment);
    }

    public void likeComment(LikeCommentRequestDto dto) {
        Comment comment = commentRepository.findById(dto.commentId());
        User user = userService.getUser(dto.userId());

        if(likeRepository.checkLike(comment, user)) {
            return;
        }

        comment.like(user);
        likeRepository.like(comment, user);
    }

    public void unlikeComment(LikeCommentRequestDto dto) {
        Comment comment = commentRepository.findById(dto.commentId());
        User user = userService.getUser(dto.userId());

        if(!likeRepository.checkLike(comment, user)) {
            return;
        }

        comment.unlike();
        likeRepository.unlike(comment, user);
    }
}
