/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdc2014.atividades;

import java.time.Duration;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.DataProvider;

public class AtividadeTest {

    @DataProvider
    Object[][] tests() {
        Dia dia1 = new Dia(Year.of(2014).atMonth(Month.MAY).atDay(20));

        dia1.novaAtividade(Atividade.Tipo.DESENVOLVIMENTO, LocalTime.of(8, 0), LocalTime.of(8, 15));
        dia1.novaAtividade(Atividade.Tipo.INTERVALO, LocalTime.of(8, 15), LocalTime.of(8, 20));
        dia1.novaAtividade(Atividade.Tipo.DESENVOLVIMENTO, LocalTime.of(8, 20), LocalTime.of(8, 50));
        dia1.novaAtividade(Atividade.Tipo.REUNIAO, LocalTime.of(8, 50), LocalTime.of(9, 15));

        Dia dia2 = new Dia(Year.of(2014).atMonth(Month.JULY).atDay(21));

        dia2.novaAtividade(Atividade.Tipo.DESENVOLVIMENTO, LocalTime.of(8, 0), LocalTime.of(8, 15));
        dia2.novaAtividade(Atividade.Tipo.INTERVALO, LocalTime.of(8, 15), LocalTime.of(8, 20));
        dia2.novaAtividade(Atividade.Tipo.DESENVOLVIMENTO, LocalTime.of(8, 20), LocalTime.of(8, 50));
        dia2.novaAtividade(Atividade.Tipo.REUNIAO, LocalTime.of(8, 50), LocalTime.of(9, 30));
        
        return new Object[][]{
            {dia1, Duration.ofMinutes(75)},
            {dia2, Duration.ofMinutes(90)}
        };
    }

    @Test(dataProvider = "tests")
    public void testDuracaoAtividades(Dia dia, Duration duracaoEsperada) {
        assertEquals(duracaoEsperada, dia.getDuracaoAtividades());
    }

}
