package org.sangyunpark99.post.domain.comment;

import org.sangyunpark99.post.domain.content.Content;

public class CommentContent extends Content {

    private static final int MAX_COMMENT_LENGTH = 100;

    public CommentContent(String contentText) {
        super(contentText);
    }

    @Override
    public void checkText(String content) {
        if(content == null || content.isEmpty()) {
            throw new IllegalArgumentException();
        }

        if(content.length() > MAX_COMMENT_LENGTH) {
            throw new IllegalArgumentException();
        }
    }
}
