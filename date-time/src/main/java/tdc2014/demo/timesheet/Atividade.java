package tdc2014.demo.timesheet;

import java.time.Duration;
import java.time.LocalDateTime;

public class Atividade {

    public enum Tipo {

        DESENVOLVIMENTO, ADMINISTRATIVA, LEGAL

    }

    private final Tipo tipo;
    private final LocalDateTime horarioInicio;
    private final LocalDateTime horarioTermino;

    public Atividade(final Tipo tipo, final LocalDateTime horarioInicio, final LocalDateTime horarioTermino) {
        this.tipo = tipo;
        this.horarioInicio = horarioInicio;
        this.horarioTermino = horarioTermino;
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

    public Duration getDuracaoBruta() {
        return Duration.between(horarioInicio, horarioTermino);
    }

    @Override
    public String toString() {
        return "Atividade {tipo = " + tipo + ", horarioInicio = " + horarioInicio + ", horarioTermino = "
                + horarioTermino + '}';
    }

}
