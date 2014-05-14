package tdc2014.temporal;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;

public class WorkingDaysYearMonthCounter implements TemporalQuery<Integer>{

    private WorkingDay utilDayQuery; 

    public WorkingDaysYearMonthCounter(Holidays holidays) {
        this.utilDayQuery = new WorkingDay(holidays);
    }
            
    @Override
    public Integer queryFrom(TemporalAccessor temporal) {
        if(!temporal.isSupported(ChronoField.YEAR) && !temporal.isSupported(ChronoField.MONTH_OF_YEAR)){
            throw new IllegalArgumentException("Argumento não suportado. Esperava um valor que suporte mês e ano.");
        }
        
        YearMonth yearMonth = YearMonth.from(temporal);
        
        Integer count = 0;
        LocalDate firstDayMonth = yearMonth.atDay(1);
        LocalDate calculatedDate = yearMonth.atEndOfMonth();
        while(calculatedDate.isAfter(firstDayMonth) || calculatedDate.isEqual(firstDayMonth) ){
            if(calculatedDate.query(utilDayQuery)){
                count++;
            }
            calculatedDate = calculatedDate.minusDays(1);
        }
        
        return count;
    }
    
}
