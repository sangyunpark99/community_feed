package org.sangyunpark99.common.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PageableTest {

    @Test
    void givenPageableIndexIsNull_whenGetOffSet_thenShouldBeReturn0() throws Exception{
        //given
        Pageable pageable = new Pageable();

        //when
        int offset = pageable.getOffset();
        int limit = pageable.getLimit();

        //then
        assertEquals(0, offset);
        assertEquals(10, limit);
    }

    @Test
    void givenPageableIndexIs2Size10_whenGetOffset_thenShouldBeReturn10() {
        //given
        Pageable pageable = new Pageable(2, 10);

        //when
        int offset = pageable.getOffset();
        int limit = pageable.getLimit();

        //then
        assertEquals(10, offset);
        assertEquals(10, limit);
    }


}
