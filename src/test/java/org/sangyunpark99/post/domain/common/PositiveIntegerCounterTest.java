package org.sangyunpark99.post.domain.common;

import org.junit.jupiter.api.Test;
import org.sangyunpark99.common.domain.PositiveIntegerCounter;

import static org.junit.jupiter.api.Assertions.*;

public class PositiveIntegerCounterTest {

    @Test
    void givenCreated_whenIncrease_thenCountIsOne() throws Exception{
        //given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();

        //when
        counter.increase();

        //then
        assertEquals(1, counter.getCount());
    }

    @Test
    void givenCreatedAndIncreased_whenDecrease_thenCountIsZero() throws Exception{
        //given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();
        counter.increase();

        //when
        counter.decrease();

        //then
        assertEquals(0, counter.getCount());
    }

    @Test
    void givenCreated_whenDecrease_thenThrowException() throws Exception{
        //given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();

        //when, then
        assertThrows(IllegalArgumentException.class, () -> counter.decrease());
    }
}
