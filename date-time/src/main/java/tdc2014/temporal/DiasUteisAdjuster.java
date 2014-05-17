package tdc2014.temporal;

import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.stream.Stream;

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
        final LocalDate from = LocalDate.from(temporal);
        return Stream.iterate(from, day -> day.plusDays(1))
                .filter(day -> day.query(diaUtilQuery))
                .skip(diasAjuste - 1)
                .findFirst().get();
    }
}
