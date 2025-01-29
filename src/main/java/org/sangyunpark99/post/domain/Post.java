package org.sangyunpark99.post.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.sangyunpark99.post.domain.content.Content;
import org.sangyunpark99.post.domain.content.PostContent;
import org.sangyunpark99.post.domain.content.PostState;
import org.sangyunpark99.user.domain.User;

@Builder
@AllArgsConstructor
public class Post {

    private final Long id;
    private final User author;
    private final Content content;
    private final Like likeCount;
    private PostState state;

    public static Post createPost(Long id, User author, String text, PostState state) {
        return new Post(id, author, new PostContent(text), state);
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
        this.likeCount = new Like();
        this.state = state;
    }

    public void like(User user) {
        if(this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        likeCount.like();
    }

    public void unlike() {
        likeCount.unlike();
    }

    public int getLikeCount() {
        return likeCount.getCount();
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
