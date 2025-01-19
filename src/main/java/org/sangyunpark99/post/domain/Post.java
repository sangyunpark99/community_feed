package org.sangyunpark99.post.domain;

import org.sangyunpark99.post.domain.content.Content;
import org.sangyunpark99.post.domain.common.Like;
import org.sangyunpark99.post.domain.content.PostContent;
import org.sangyunpark99.post.domain.content.PostState;
import org.sangyunpark99.user.domain.User;

public class Post {

    private final Long id;
    private final User author;
    private final Content content;
    private final Like likeManager;
    private PostState state;

    public static Post createPost(Long id, User author, String text, PostState state) {
        return new Post(id, author, new PostContent(text), state);
    }

    public static Post createDefaultPost(Long id, User author, String text) {
        return new Post(id, author, new PostContent(text), PostState.PUBLIC);
    }

    public Post(Long id, User author, Content content) {
        this(id, author, content, PostState.PUBLIC);
    }

    public Post(final Long id, User author, Content content, PostState state) {
        if(author == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.likeManager = new Like();
        this.state = state;
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

    public Content getContent() {
        return content;
    }

    public String getContentText() {
        return content.getContentText();
    }

    public PostState getState() {
        return state;
    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public void updatePost(User user, String updatedContent, PostState state) {
        if(!this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        this.state = state;
        this.content.updateContent(updatedContent);
    }
}
