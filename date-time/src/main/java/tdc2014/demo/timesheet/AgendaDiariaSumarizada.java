/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdc2014.demo.timesheet;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.time.Duration;
import java.util.List;

public final class AgendaDiariaSumarizada {

    private final List<AtividadeSumarizada> agendaDiariaSumarizada;

    public AgendaDiariaSumarizada(List<Atividade> agendaDiaria) {
        agendaDiariaSumarizada = agendaDiaria.stream()
                .map(atividade -> {
                    final Duration duracaoAtividadesIntercaladas = agendaDiaria.stream()
                    .filter(intercalada -> intercalada.getHorarioInicio().isAfter(atividade.getHorarioInicio())
                            && intercalada.getHorarioInicio().isBefore(atividade.getHorarioTermino()))
                    .map(Atividade::getDuracao)
                    .reduce(Duration.ZERO, Duration::plus);
                    return new AtividadeSumarizada(atividade.getTipo(), atividade.getHorarioInicio(),
                            atividade.getHorarioTermino(), atividade.getDuracao().minus(duracaoAtividadesIntercaladas));
                })
                .sorted(comparing(AtividadeSumarizada::getHorarioInicio))
                .collect(toList());
    }

    public List<AtividadeSumarizada> getAgendaDiariaSumarizada() {
        return agendaDiariaSumarizada;
    }
}
