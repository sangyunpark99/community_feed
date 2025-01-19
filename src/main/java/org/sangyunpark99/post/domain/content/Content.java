package org.sangyunpark99.common.domain;

public abstract class Content {
    String contentText;

    public Content(String contentText) {
        checkText(contentText);
        this.contentText = contentText;
    }

    public abstract void checkText(String contentText);

    public String getContentText() {
        return contentText;
    }
}
