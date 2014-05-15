package tdc2014.temporal;

import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class DiasUteisAdjuster implements TemporalAdjuster {

    private long diasAjuste;

    private final DiaUtilQuery diaUtilQuery;

    public DiasUteisAdjuster() {
        this(1);
    }

    public DiasUteisAdjuster(long diasAjuste) {
        this.diasAjuste = diasAjuste;
        this.diaUtilQuery = new DiaUtilQuery(FeriadosFactory.create());
    }

    @Override
    public Temporal adjustInto(final Temporal temporal) {
        LocalDate date = nextOrSameUtilDay(LocalDate.from(temporal));
        for (int i = 0; i < diasAjuste - 1; i++) {
            date = nextOrSameUtilDay(date.plusDays(1));
        }
        return date;
    }

    private LocalDate nextOrSameUtilDay(final LocalDate date) {
        if (!date.query(diaUtilQuery)) {
            return nextOrSameUtilDay(date.plusDays(1));
        }

        return date;
    }

}
