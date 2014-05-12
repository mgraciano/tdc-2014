package tdc2014.adjuster;

import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class PayDayAdjuster implements TemporalAdjuster{

    @Override
    public Temporal adjustInto(Temporal temporal) {
        LocalDate date = LocalDate.from(temporal);        
        LocalDate firtsDayOfMonth = date.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate payDay = firtsDayOfMonth.with(new UtilDaysAdjuster(5));

        if(date.isAfter(payDay)){
            return date.with(TemporalAdjusters.firstDayOfNextMonth()).with(this);
        }
        return payDay;
    }

}
