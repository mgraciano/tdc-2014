package tdc2014.adjuster;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class UtilDaysAdjuster implements TemporalAdjuster {

    private long daysToAdjust;
    
    private Holidays holidays;

    public UtilDaysAdjuster() {
        this(1);
    }
    
    public UtilDaysAdjuster(long daysToAdjust) {
        this.daysToAdjust = daysToAdjust;
        this.holidays = new Holidays();
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
        LocalDate date = nextOrSameUtilDay(LocalDate.from(temporal));
        for (int i = 0; i < daysToAdjust - 1; i++) {
            date = nextOrSameUtilDay(date.plusDays(1));
        }
        return date;
    }

    private LocalDate nextOrSameUtilDay(LocalDate date) {
        if (date.getDayOfWeek() == DayOfWeek.SATURDAY
                || date.getDayOfWeek() == DayOfWeek.SUNDAY
                || holidays.isHoliday(date)) {
            date = nextOrSameUtilDay(date.plusDays(1));
        }
        return date;
    }
    
}