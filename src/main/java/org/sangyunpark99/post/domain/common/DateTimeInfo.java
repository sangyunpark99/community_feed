package org.sangyunpark99.post.domain.common;

import java.time.LocalDateTime;

public class DateTimeInfo {

    private boolean isModified;
    private LocalDateTime dateTime;

    public DateTimeInfo() {
        isModified = false;
        dateTime = LocalDateTime.now();
    }

    public boolean isModified() {
        return isModified;
    }

    public void updateEditDateTime() {
        isModified = true;
        this.dateTime = LocalDateTime.now();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
