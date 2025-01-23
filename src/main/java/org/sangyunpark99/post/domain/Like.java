package org.sangyunpark99.post.domain;

import org.sangyunpark99.common.domain.PositiveIntegerCounter;

public class Like {

    private final PositiveIntegerCounter counter;

    public Like() {
        this.counter = new PositiveIntegerCounter();
    }

    public Like(int count) {
        this.counter = new PositiveIntegerCounter(count);
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
