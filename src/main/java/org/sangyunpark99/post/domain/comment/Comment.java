package org.sangyunpark99.post.domain.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.sangyunpark99.post.domain.Like;
import org.sangyunpark99.post.domain.Post;
import org.sangyunpark99.post.domain.content.CommentContent;
import org.sangyunpark99.user.domain.User;

@Builder
@AllArgsConstructor
public class Comment {

    private final Long id;

    private final Post post;

    private final User author;

    private final CommentContent content;
    private final Like likeManager;

    public static Comment createComment(Post post, User user, String content) {
        return new Comment(null, new CommentContent(content), post, user);
    }

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

    public Long getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public User getAuthor() {
        return author;
    }

    public CommentContent getCommentContent() {
        return content;
    }

    public void like(User user) {

        if(this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        likeManager.like();
    }

    public void unlike() {
        likeManager.unlike();
    }

    public int getLikeCount() {
        return likeManager.getCount();
    }

    public void updateComment(User user, String updatedContent) {
        if(!this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        this.content.updateContent(updatedContent);
    }

    public String getContent() {
        return content.getContentText();
    }
}