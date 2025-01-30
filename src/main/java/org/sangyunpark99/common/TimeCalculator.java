package org.sangyunpark99.common;

import java.time.LocalDate;

public class TimeCalculator {

    private TimeCalculator() {

    }

    public static LocalDate getDateDaysAgo(int daysAgo) {
        LocalDate curDate = LocalDate.now();
        return curDate.minusDays(daysAgo);
    }
}
