package tdc2014.demo.timesheet;

import java.time.Duration;
import java.time.LocalDateTime;
import tdc2014.demo.timesheet.Atividade.Tipo;

public final class AtividadeSumarizada {

    private final Tipo tipo;
    private final LocalDateTime horarioInicio;
    private final LocalDateTime horarioTermino;
    private final Duration duracao;

    AtividadeSumarizada(final Tipo tipo, final LocalDateTime horarioInicio, final LocalDateTime horarioTermino,
            final Duration duracao) {
        this.tipo = tipo;
        this.horarioInicio = horarioInicio;
        this.horarioTermino = horarioTermino;
        this.duracao = duracao;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public LocalDateTime getHorarioInicio() {
        return horarioInicio;
    }

    public LocalDateTime getHorarioTermino() {
        return horarioTermino;
    }

    public Duration getDuracao() {
        return duracao;
    }

}
