package org.sangyunpark99.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeCalculator {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM:SS");

    private TimeCalculator() {

    }

    public static LocalDate getDateDaysAgo(int daysAgo) {
        LocalDate curDate = LocalDate.now();
        return curDate.minusDays(daysAgo);
    }

    public static String getFormattedDate(LocalDateTime dateTime) {
        if(dateTime == null) {
            return "";
        }

        return dateTime.format(FORMATTER);
    }
}
