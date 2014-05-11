package tdc2014.adjuster;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class PlusUtilDaysAdjuster implements TemporalAdjuster {

    private long days;

    public PlusUtilDaysAdjuster() {
        this(0);
    }

    public PlusUtilDaysAdjuster(long days) {
        this.days = days;
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
        LocalDate date = nextOrSameUtilDay(LocalDate.from(temporal));
        for (int day = 1; day <= days; day++) {
            date = nextOrSameUtilDay(date.plusDays(1));
        }
        return date;
    }

    private LocalDate nextOrSameUtilDay(LocalDate date) {
        if (date.getDayOfWeek() == DayOfWeek.SATURDAY
                || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        }
        return date;
    }

}