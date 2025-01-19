package org.sangyunpark99.post.domain.common;

public class Like {

    private final PositiveIntegerCounter counter;

    public Like() {
        this.counter = new PositiveIntegerCounter();
    }

    public void like() {
        counter.increase();
    }

    public void unlike() {
        this.counter.decrease();
    }

    public int getCount() {
        return counter.getCount();
    }
}
