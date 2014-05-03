/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdc2014.atividades;

import java.time.Duration;
import java.time.LocalDateTime;

public class Atividade {

    public enum Tipo {

        DESENVOLVIMENTO, INTERVALO, TELEFONE, REUNIAO
    };
    private Tipo tipo;
    private Duration duracao;

    public Atividade(Tipo tipo, LocalDateTime inicio, LocalDateTime fim) {
        this.tipo = tipo;
        this.duracao = Duration.between(inicio, fim);
    }

    public Duration getDuracao() {
        return duracao;
    }

    public Tipo getTipo() {
        return tipo;
    }
}
