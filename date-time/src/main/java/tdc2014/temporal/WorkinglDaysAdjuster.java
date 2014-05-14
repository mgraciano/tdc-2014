package tdc2014.temporal;

import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class WorkinglDaysAdjuster implements TemporalAdjuster {

    private long daysToAdjust;
    
    private WorkingDay utilDayQuery;

    public WorkinglDaysAdjuster(Holidays holidays) {
        this(1, holidays);
    }
    
    public WorkinglDaysAdjuster(long daysToAdjust, Holidays holidays) {
        this.daysToAdjust = daysToAdjust;
        this.utilDayQuery = new WorkingDay(holidays);
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
        if (!date.query(utilDayQuery)) {
            date = nextOrSameUtilDay(date.plusDays(1));
        }
        return date;
    }
    
}