package tdc2014.temporal;

import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;
import java.util.Set;

public class FeriadosQuery implements TemporalQuery<Boolean> {

    private final Set<LocalDate> feriados;

    public FeriadosQuery(final Set<LocalDate> feriados) {
        this.feriados = feriados;
    }

    public boolean isHoliday(final LocalDate date) {
        return feriados.contains(date);
    }

    @Override
    public Boolean queryFrom(final TemporalAccessor temporalAccessor) {
        return isHoliday(LocalDate.from(temporalAccessor));
    }
}
