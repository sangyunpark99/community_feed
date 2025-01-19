package org.sangyunpark99.post.domain.common;

import java.time.LocalDateTime;

public class ModificationInfo {

    private boolean isModified;
    private LocalDateTime dateTime;

    public ModificationInfo() {
        isModified = false;
        dateTime = LocalDateTime.now();
    }

    public boolean isModified() {
        return isModified;
    }

    public void modify() {
        isModified = true;
        this.dateTime = LocalDateTime.now();
    }
}
