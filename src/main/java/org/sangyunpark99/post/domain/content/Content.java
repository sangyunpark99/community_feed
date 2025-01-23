package org.sangyunpark99.post.domain.content;

public abstract class Content {

    private String contentText;
    private final DateTimeInfo dateTimeInfo;

    public Content(String contentText) {
        checkText(contentText);
        this.contentText = contentText;
        this.dateTimeInfo = new DateTimeInfo();
    }

    public abstract void checkText(String contentText);

    public void updateContent(String updateContent) {
        checkText(updateContent);
        this.contentText = updateContent;
        dateTimeInfo.updateEditDateTime();
    }

    public String getContentText() {
        return contentText;
    }
}
