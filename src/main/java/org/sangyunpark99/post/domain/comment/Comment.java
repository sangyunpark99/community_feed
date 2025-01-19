package org.sangyunpark99.post.domain.comment;

import org.sangyunpark99.post.domain.common.Like;
import org.sangyunpark99.post.domain.Post;
import org.sangyunpark99.post.domain.content.CommentContent;
import org.sangyunpark99.user.domain.User;

public class Comment {

    private final Long id;
    private final Post post;
    private final User author;
    private final CommentContent content;
    private final Like likeManager;

    public Comment(Long id, CommentContent content, Post post, User author) {

        if(author == null) {
            throw new IllegalArgumentException();
        }

        if(post == null) {
            throw new IllegalArgumentException();
        }

        if(content == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.post = post;
        this.author = author;
        this.content = content;
        this.likeManager = new Like();
    }

    public void like(User user) {
        likeManager.like(user, author);
    }

    public void unlike() {
        likeManager.unlike();
    }

    public int getLikeCount() {
        return likeManager.getCount();
    }
}