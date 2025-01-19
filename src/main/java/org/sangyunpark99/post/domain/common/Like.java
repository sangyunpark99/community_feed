package org.sangyunpark99.post.domain.like;

import org.sangyunpark99.common.domain.PositiveIntegerCounter;
import org.sangyunpark99.user.domain.User;

public class Like {

    private final PositiveIntegerCounter counter;

    public Like() {
        this.counter = new PositiveIntegerCounter();
    }

    public void like(User user, User author) {
        if(user.equals(author)) {
            throw new IllegalArgumentException();
        }

        counter.increase();
    }

    public void unlike() {
        this.counter.decrease();
    }

    public int getCount() {
        return counter.getCount();
    }
}
