package tdc2014.temporal;

import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.stream.Stream;

/**
 * Um {@link TemporalAdjuster} responsável por ajustar uma {@link Temporal data} informada para o dia útil mais próximo.
 * Caso a data informada seja um dia útil, ela mesma será retornada.
 *
 * É possível ainda determinar quantos dias úteis devem ser avançados. Por exemplo, para obter-se o 5o dia útil a partir
 * de uma data, o construtor {@link DiasUteisAdjuster#DiasUteisAdjuster(long)} deve ser utilizado informando o valor
 * {@code 5}.
 */
public class DiasUteisAdjuster implements TemporalAdjuster {

    private long quantidadeDias;

    private final DiaUtilQuery diaUtilQuery;

    /**
     * Cria um {@link TemporalAdjuster} responsável por ajustar uma {@link Temporal data} informada para o dia útil mais
     * próximo. Caso a data informada seja um dia útil, ela mesma será retornada.
     */
    public DiasUteisAdjuster() {
        this(1);
    }

    /**
     * Cria um {@link TemporalAdjuster} responsável por ajustar uma {@link Temporal data} informada para o dia útil mais
     * próximo, dado a {@link DiasUteisAdjuster#DiasUteisAdjuster(long) quantidadede dias} informada.
     *
     * Para obter o 5o dia util a partir de uma data, informe {@code 5} como parâmetro.
     *
     * @param quantidadeDias
     */
    public DiasUteisAdjuster(final long quantidadeDias) {
        this.quantidadeDias = quantidadeDias;
        this.diaUtilQuery = new DiaUtilQuery(FeriadosFactory.create());
    }

    @Override
    public Temporal adjustInto(final Temporal temporal) {
        final LocalDate from = LocalDate.from(temporal);
        return Stream.iterate(from, day -> day.plusDays(1))
                .filter(day -> day.query(diaUtilQuery))
                .skip(quantidadeDias - 1)
                .findFirst().get();
    }
}
