package org.sangyunpark99.post.domain.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sangyunpark99.post.domain.content.DateTimeInfo;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeInfoTest {

    @Test
    @DisplayName("")
    void givenCreated_whenUpdated_thenTimeAndStateArsUpdated() throws Exception{
        //given
        DateTimeInfo dateTimeInfo = new DateTimeInfo();
        LocalDateTime localDateTime = dateTimeInfo.getDateTime();

        //when
        dateTimeInfo.updateEditDateTime();

        //then
        assertTrue(dateTimeInfo.isModified());
        assertNotEquals(localDateTime, dateTimeInfo.getDateTime());
    }
}
