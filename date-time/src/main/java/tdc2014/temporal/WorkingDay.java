package tdc2014.temporal;

import static java.time.LocalDate.from;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;

public class WorkingDay implements TemporalQuery<Boolean> {

    private Holidays holidays;

    public WorkingDay(Holidays holidays) {
        this.holidays = holidays;
    }

    @Override
    public Boolean queryFrom(TemporalAccessor temporal) {
        LocalDate date = from(temporal);

        return date.getDayOfWeek() != DayOfWeek.SATURDAY 
                && date.getDayOfWeek() != DayOfWeek.SUNDAY 
                && !date.query(holidays);
    }

}
