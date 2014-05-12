package tdc2014.atividades;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

class ContabilizadorInterrupcao {

    private Atividade atividadeInterrompida;
    private Map<Atividade, Duration> interrupcoes = new HashMap<>();

    ContabilizadorInterrupcao(Atividade atividadeInterrompida) {
        this.atividadeInterrompida = atividadeInterrompida;
    }

    private Duration getDuracao(Atividade novaAtividade) {
        LocalDateTime inicio = this.atividadeInterrompida.getInicio();
        LocalDateTime fim = this.atividadeInterrompida.getFim();

        Duration duration = Duration.ZERO;
        if ((inicio.isBefore(novaAtividade.getInicio()) || inicio.isEqual(novaAtividade.getInicio()))
                && (fim.isAfter(novaAtividade.getFim()) || fim.isEqual(novaAtividade.getFim()))) {
            duration = novaAtividade.getDuracao();
        } else if ((inicio.isBefore(novaAtividade.getInicio()) || inicio.isEqual(novaAtividade.getInicio()))
                && (fim.isBefore(novaAtividade.getFim()) || fim.isEqual(novaAtividade.getFim()))
                && fim.isAfter(novaAtividade.getInicio())) {
            duration = Duration.between(novaAtividade.getInicio(), fim);
        } else if ((inicio.isAfter(novaAtividade.getInicio()) || inicio.isEqual(novaAtividade.getInicio()))
                && (fim.isAfter(novaAtividade.getFim())) || fim.isEqual(novaAtividade.getFim())
                && inicio.isBefore(novaAtividade.getFim())) {
            duration = Duration.between(inicio, novaAtividade.getFim());
        }

        return duration.minus(novaAtividade.getDuracaoIterruptor());
    }

    void adiciona(Atividade novaAtividade) {
        Duration duration = getDuracao(novaAtividade);

        if (!duration.isZero()) {
            interrupcoes.put(novaAtividade, duration);
            novaAtividade.registraDuracaoIterruptor(duration);
        }
    }

    public Map<Atividade, Duration> getInterrupcoes() {
        return interrupcoes;
    }

    public Duration getDuracao() {
        return interrupcoes.values()
                .stream()
                .reduce(Duration.ZERO, Duration::plus);
    }

    @Override
    public String toString() {
        return "ContabilizadorInterrupcao{" + "interrupcoes=" + interrupcoes + '}';
    }

}
