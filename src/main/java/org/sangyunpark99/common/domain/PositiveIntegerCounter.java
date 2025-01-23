package org.sangyunpark99.common.domain;

public class PositiveIntegerCounter {
    private int count;

    public PositiveIntegerCounter() {
        this.count = 0;
    }

    public PositiveIntegerCounter(int count) {
        this.count = count;
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
