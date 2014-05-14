package tdc2014.temporal;

import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;
import java.util.Set;

public class Holidays implements TemporalQuery<Boolean>{

    private Set<LocalDate> holidays;

    public Holidays(Set<LocalDate> holidays) {
        this.holidays = holidays;
    }

    public boolean isHoliday(LocalDate date) {
        return holidays.contains(date);
    }

    @Override
    public Boolean queryFrom(TemporalAccessor temporalAccessor) {
        return isHoliday(LocalDate.from(temporalAccessor));
    }
}
