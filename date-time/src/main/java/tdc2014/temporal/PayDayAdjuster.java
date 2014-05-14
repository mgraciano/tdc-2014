package tdc2014.temporal;

import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class PayDayAdjuster implements TemporalAdjuster{

    private WorkinglDaysAdjuster fifthWorkingDayAdjuster;

    public PayDayAdjuster(Holidays holidays) {
        this.fifthWorkingDayAdjuster = new WorkinglDaysAdjuster(5, holidays);
    }
    
    @Override
    public Temporal adjustInto(Temporal temporal) {
        LocalDate date = LocalDate.from(temporal);        
        LocalDate firtsDayOfMonth = date.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate payDay = firtsDayOfMonth.with(fifthWorkingDayAdjuster);

        if(date.isAfter(payDay)){
            return date.with(TemporalAdjusters.firstDayOfNextMonth()).with(this);
        }
        return payDay;
    }

}
