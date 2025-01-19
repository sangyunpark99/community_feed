package org.sangyunpark99.post.domain.content;

public class PostContent extends Content {

    private static final int MIN_POST_LENGTH = 5;
    private static final int MAX_POST_LENGTH = 500;

    public PostContent(String content) {
        super(content);
    }

    @Override
    public void checkText(String content) {
        if(content == null || content.isEmpty()) {
            throw new IllegalArgumentException();
        }

        if(content.length() > MAX_POST_LENGTH) {
            throw new IllegalArgumentException();
        }

        if(content.length() < MIN_POST_LENGTH ) {
            throw new IllegalArgumentException();
        }
    }
}
