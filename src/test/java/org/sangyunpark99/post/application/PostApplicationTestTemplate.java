package org.sangyunpark99.post.application;

import org.sangyunpark99.fake.FakeObjectFactory;
import org.sangyunpark99.post.application.dto.CreateCommentRequestDto;
import org.sangyunpark99.post.application.dto.CreatePostRequestDto;
import org.sangyunpark99.post.domain.Post;
import org.sangyunpark99.post.domain.content.PostState;
import org.sangyunpark99.user.application.UserService;
import org.sangyunpark99.user.application.dto.CreateUserRequestDto;
import org.sangyunpark99.user.domain.User;

public class PostApplicationTestTemplate {
    protected final UserService userService = FakeObjectFactory.getUserService();
    protected final PostService postService = FakeObjectFactory.getPostService();
    protected final CommentService commentService = FakeObjectFactory.getCommentService();

    protected final User user = userService.createUser(new CreateUserRequestDto("user1", ""));
    protected final User otherUser = userService.createUser(new CreateUserRequestDto("user2", ""));

    protected final CreatePostRequestDto postRequestDto = new CreatePostRequestDto(user.getId(), "this is test content",
            PostState.PUBLIC);

    protected final Post post = postService.create(postRequestDto);

    protected final CreateCommentRequestDto commentRequestDto = new CreateCommentRequestDto("this is test comment",
            post.getId(),user.getId());
}
