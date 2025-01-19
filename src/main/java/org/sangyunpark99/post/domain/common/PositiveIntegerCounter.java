package org.sangyunpark99.post.domain.common;

public class PositiveIntegerCounter {
    private int count;

    public PositiveIntegerCounter() {
        this.count = 0;
    }

    public void increase() {
        this.count++;
    }

    public void decrease() {
        if(count < 1) {
            throw new IllegalArgumentException();
        }
        this.count--;
    }

    public int getCount() {
        return count;
    }
}
