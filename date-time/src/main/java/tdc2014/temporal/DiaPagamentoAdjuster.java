package tdc2014.temporal;

import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class DiaPagamentoAdjuster implements TemporalAdjuster {

    private final DiasUteisAdjuster quintoDiaUtilAdjuster;

    public DiaPagamentoAdjuster(final FeriadosQuery feriados) {
        this.quintoDiaUtilAdjuster = new DiasUteisAdjuster(5);
    }

    @Override
    public Temporal adjustInto(final Temporal temporal) {
        final LocalDate date = LocalDate.from(temporal);
        final LocalDate firtsDayOfMonth = date.with(TemporalAdjusters.firstDayOfMonth());
        final LocalDate payDay = firtsDayOfMonth.with(quintoDiaUtilAdjuster);

        if (date.isAfter(payDay)) {
            return date.with(TemporalAdjusters.firstDayOfNextMonth()).with(this);
        }
        return payDay;
    }

}
