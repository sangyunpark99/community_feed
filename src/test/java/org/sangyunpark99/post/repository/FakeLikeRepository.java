package org.sangyunpark99.post.repository;

import org.sangyunpark99.post.application.interfaces.LikeRepository;
import org.sangyunpark99.post.domain.Post;
import org.sangyunpark99.post.domain.comment.Comment;
import org.sangyunpark99.user.domain.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FakeLikeRepository implements LikeRepository {

    private final Map<Post, Set<User>> postLike = new HashMap<>();
    private final Map<Comment, Set<User>> commentLike = new HashMap<>();

    @Override
    public boolean checkLike(Post post, User user) {
        if(postLike.get(post) == null) {
            return false;
        }
        return postLike.get(post).contains(user);
    }

    @Override
    public boolean checkLike(Comment comment, User user) {
        if(commentLike.get(comment) == null) {
            return false;
        }

        return commentLike.get(comment).contains(user);
    }

    @Override
    public void like(Post post, User user) {
        Set<User> users = postLike.get(post);
        if(users == null) {
            users = new HashSet<>();
        }

        users.add(user);
        postLike.put(post, users);
    }

    @Override
    public void unlike(Post post, User user) {
        Set<User> users = postLike.get(post);
        if(users == null) {
            return;
        }

        users.remove(user);
        postLike.put(post, users);
    }

    @Override
    public void like(Comment comment, User user) {
        Set<User> users = postLike.get(comment);
        if(users == null) {
            users = new HashSet<>();
        }

        users.add(user);
        commentLike.put(comment, users);
    }

    @Override
    public void unlike(Comment comment, User user) {
        Set<User> users = commentLike.get(comment);
        if(users == null) {
            return;
        }

        users.remove(user);
        commentLike.put(comment, users);
    }
}
