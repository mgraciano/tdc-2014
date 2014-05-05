package tdc2014.atividades;

import java.time.Duration;
import java.time.LocalDateTime;

public class Atividade {

    private Tipo tipo;
    private Duration duracaoIterruptor = Duration.ZERO;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private ContabilizadorInterrupcao contabilizadorInterrupcao;

    public Atividade(Tipo tipo, LocalDateTime inicio, LocalDateTime fim) {
        this.tipo = tipo;
        this.inicio = inicio;
        this.fim = fim;
        this.contabilizadorInterrupcao = new ContabilizadorInterrupcao(this);
    }

    public Duration getDuracao() {
        Duration duracaoIterrupcoes = contabilizadorInterrupcao.getDuracao();
        return Duration.between(inicio, fim).minus(duracaoIterrupcoes);
    }

    void interrompe(Atividade novaAtividade) {
        contabilizadorInterrupcao.adiciona(novaAtividade);
    }

    public Tipo getTipo() {
        return tipo;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }
    
    void registraDuracaoIterruptor(Duration duration) {
        this.duracaoIterruptor = duracaoIterruptor.plus(duration);
    }

    public Duration getDuracaoIterruptor() {
        return duracaoIterruptor;
    }
    
    Duration getSaldoIterruptor() {
        return getDuracao().minus(duracaoIterruptor);
    }

    @Override
    public String toString() {
        return "Atividade{" + "tipo=" + tipo + ", duracaoIterruptor=" + duracaoIterruptor + ", inicio=" + inicio + ", fim=" + fim + ", contabilizadorInterrupcao=" + contabilizadorInterrupcao + '}';
    }
    
}