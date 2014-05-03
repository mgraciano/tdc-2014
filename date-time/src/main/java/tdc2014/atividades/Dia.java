/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdc2014.atividades;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Dia {

    private LocalDate dia;
    private List<Atividade> atividades = new ArrayList<>();

    public Dia(LocalDate dia) {
        this.dia = dia;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public Atividade novaAtividade(Atividade.Tipo tipo, LocalTime horaInicio, LocalTime horaFim) {
        Atividade atividade = new Atividade(tipo, LocalDateTime.of(dia, horaInicio), LocalDateTime.of(dia, horaFim));

        this.atividades.add(atividade);

        return atividade;
    }

    public Duration getDuracaoAtividades() {
        return atividades
                .stream()
                .map(Atividade::getDuracao)
                .reduce(Duration.ZERO, (d1, d2) -> d1.plus(d2));
    }
}
