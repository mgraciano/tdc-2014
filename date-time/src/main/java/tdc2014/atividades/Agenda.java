package tdc2014.atividades;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Agenda {

    private LocalDate data;
    private List<Atividade> atividades = new ArrayList<>();

    public Agenda(LocalDate data) {
        this.data = data;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public Atividade fazerIntervalo(LocalTime horaInicio, LocalTime horaFim) {
        return novaAtividade(Tipo.INTERVALO, horaInicio, horaFim);
    }

    public Atividade concederSuporte(LocalTime horaInicio, LocalTime horaFim) {
        return novaAtividade(Tipo.SUPORTE, horaInicio, horaFim);
    }

    public Atividade desenvolver(LocalTime horaInicio, LocalTime horaFim) {
        return novaAtividade(Tipo.DESENVOLVIMENTO, horaInicio, horaFim);
    }

    public Atividade participarReuniao(LocalTime horaInicio, LocalTime horaFim) {
        return novaAtividade(Tipo.REUNIAO, horaInicio, horaFim);
    }

    public Atividade usarTelefone(LocalTime horaInicio, LocalTime horaFim) {
        return novaAtividade(Tipo.TELEFONE, horaInicio, horaFim);
    }

    public Atividade novaAtividade(Tipo tipo, LocalTime horaInicio, LocalTime horaFim) {
        Atividade novaAtividade = new Atividade(tipo, LocalDateTime.of(data, horaInicio), LocalDateTime.of(data, horaFim));

        registraInterrupcao(novaAtividade);

        atividades.add(novaAtividade);

        return novaAtividade;
    }

    public Duration getDuracaoAtividades() {
        return atividades
                .stream()
                .map(Atividade::getDuracao)
                .reduce(Duration.ZERO, Duration::plus);
    }

    private void registraInterrupcao(Atividade novaAtividade) {
        atividades
                .stream()
                .sorted((Atividade t, Atividade t1) -> Integer.compare(t1.getInicio().compareTo(t.getInicio()), t1.getFim().compareTo(t.getFim())))
                .forEach((Atividade atividade) -> {
                    if (!novaAtividade.getSaldoIterruptor().isZero()) {
                        atividade.interrompe(novaAtividade);
                    }
                });
    }

    public Map<Tipo, Duration> getDuracaoAtividadesPorTipo() {
        Map<Tipo, Duration> duracaoPorTipo = new HashMap<>();

        duracaoPorTipo.put(Tipo.SUPORTE, getDuracaoAtividades(Tipo.SUPORTE));
        duracaoPorTipo.put(Tipo.DESENVOLVIMENTO, getDuracaoAtividades(Tipo.DESENVOLVIMENTO));
        duracaoPorTipo.put(Tipo.INTERVALO, getDuracaoAtividades(Tipo.INTERVALO));
        duracaoPorTipo.put(Tipo.REUNIAO, getDuracaoAtividades(Tipo.REUNIAO));
        duracaoPorTipo.put(Tipo.TELEFONE, getDuracaoAtividades(Tipo.TELEFONE));

        return duracaoPorTipo;
    }

    public Duration getDuracaoAtividades(Tipo tipo) {
        return getAtividades().stream()
                .filter(t -> t.getTipo() == tipo)
                .map(Atividade::getDuracao)
                .reduce(Duration.ZERO, Duration::plus);
    }

//    public static void main(String[] args) {
//        Agenda agenda3 = new Agenda(Year.of(2014).atMonth(Month.MAY).atDay(20));
//
//        agenda3.desenvolver(LocalTime.of(13, 0), LocalTime.of(18, 0));
//        agenda3.concederSuporte(LocalTime.of(13, 0), LocalTime.of(15, 0));
//        agenda3.fazerIntervalo(LocalTime.of(14, 0), LocalTime.of(14, 15));
//        agenda3.concederSuporte(LocalTime.of(14, 15), LocalTime.of(16, 30));
//        agenda3.concederSuporte(LocalTime.of(17, 15), LocalTime.of(18, 0));
//
//        agenda3.atividades
//                .stream()
//                .sorted((Atividade t, Atividade t1) -> Integer.compare(t1.getInicio().compareTo(t.getInicio()), t1.getFim().compareTo(t.getFim())) )
//                .forEach((Atividade atividade) -> {
//                    System.out.println(atividade);
//                });
//        
//        System.out.println(agenda3.getDuracaoAtividadesPorTipo());
//        
//    }
}
