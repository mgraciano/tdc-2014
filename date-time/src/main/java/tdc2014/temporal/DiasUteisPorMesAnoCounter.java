package tdc2014.temporal;

import java.time.YearMonth;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;
import java.util.stream.Stream;

public class DiasUteisPorMesAnoCounter implements TemporalQuery<Long> {

    private DiaUtilQuery diaUtilQuery;

    public DiasUteisPorMesAnoCounter(FeriadosQuery feriados) {
        this.diaUtilQuery = new DiaUtilQuery(feriados);
    }

    @Override
    public Long queryFrom(TemporalAccessor temporal) {
        if (!temporal.isSupported(ChronoField.YEAR) && !temporal.isSupported(ChronoField.MONTH_OF_YEAR)) {
            throw new IllegalArgumentException("Argumento não suportado. Esperava um valor que suporte mês e ano.");
        }

        final YearMonth mesAno = YearMonth.from(temporal);

        return Stream.iterate(mesAno.atDay(1), day -> day.plusDays(1))
                .limit(mesAno.lengthOfMonth())
                .filter(day -> day.query(diaUtilQuery))
                .count();
    }

}
