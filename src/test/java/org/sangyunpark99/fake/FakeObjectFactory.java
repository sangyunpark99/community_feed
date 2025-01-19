package org.sangyunpark99.fake;

import org.sangyunpark99.post.application.CommentService;
import org.sangyunpark99.post.application.PostService;
import org.sangyunpark99.post.application.interfaces.CommentRepository;
import org.sangyunpark99.post.application.interfaces.LikeRepository;
import org.sangyunpark99.post.application.interfaces.PostRepository;
import org.sangyunpark99.post.domain.repository.FakeCommentRepository;
import org.sangyunpark99.post.domain.repository.FakeLikeRepository;
import org.sangyunpark99.post.domain.repository.FakePostRepository;
import org.sangyunpark99.user.application.UserRelationService;
import org.sangyunpark99.user.application.UserService;
import org.sangyunpark99.user.application.interfaces.UserRelationRepository;
import org.sangyunpark99.user.application.interfaces.UserRepository;
import org.sangyunpark99.user.repository.FakeUserRelationRepository;
import org.sangyunpark99.user.repository.FakeUserRepository;

public class FakeObjectFactory {

    private static final UserRepository userRepository = new FakeUserRepository();
    private static final UserRelationRepository userRelationRepository = new FakeUserRelationRepository();
    private static final PostRepository postRepository = new FakePostRepository();
    private static final CommentRepository commentRepository = new FakeCommentRepository();
    private static final LikeRepository likeRepository = new FakeLikeRepository();

    private static final UserService userService = new UserService(userRepository);
    private static final UserRelationService userRelationService = new UserRelationService(userService,
            userRelationRepository);
    private static final PostService postService = new PostService(postRepository,likeRepository,userService);
    private static final CommentService commentService = new CommentService(commentRepository,userService,postService
            ,likeRepository);


    private FakeObjectFactory() {

    }

    public static PostService getPostService() {
        return postService;
    }

    public static UserService getUserService() {
        return userService;
    }

    public static CommentService getCommentService() {
        return commentService;
    }

    public static UserRelationService userRelationService() {
        return userRelationService;
    }
}
