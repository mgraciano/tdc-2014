package tdc2014.atividades;

import java.time.Duration;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.DataProvider;

public class AgendaTest {

    @DataProvider
    Object[][] tests() {
        Agenda agenda1 = new Agenda(Year.of(2014).atMonth(Month.MAY).atDay(20));

        agenda1.desenvolver(LocalTime.of(8, 0), LocalTime.of(12, 0));
        agenda1.fazerIntervalo(LocalTime.of(9, 0), LocalTime.of(10, 0));
        agenda1.participarReuniao(LocalTime.of(10, 30), LocalTime.of(11, 0));

        Agenda agenda2 = new Agenda(Year.of(2014).atMonth(Month.MAY).atDay(20));

        agenda2.desenvolver(LocalTime.of(13, 0), LocalTime.of(18, 0));
        agenda2.participarReuniao(LocalTime.of(14, 0), LocalTime.of(16, 0));
        agenda2.fazerIntervalo(LocalTime.of(15, 0), LocalTime.of(15, 30));
        agenda2.concederSuporte(LocalTime.of(15, 40), LocalTime.of(16, 30));

        Agenda agenda3 = new Agenda(Year.of(2014).atMonth(Month.MAY).atDay(20));

        agenda3.desenvolver(LocalTime.of(13, 0), LocalTime.of(18, 0));
        agenda3.concederSuporte(LocalTime.of(13, 0), LocalTime.of(15, 0));
        agenda3.fazerIntervalo(LocalTime.of(14, 0), LocalTime.of(14, 15));
        agenda3.concederSuporte(LocalTime.of(14, 15), LocalTime.of(16, 30));
        agenda3.concederSuporte(LocalTime.of(17, 15), LocalTime.of(18, 0));

        Agenda agenda4 = new Agenda(Year.of(2014).atMonth(Month.MAY).atDay(20));

        agenda4.desenvolver(LocalTime.of(8, 0), LocalTime.of(11, 0));
        agenda4.participarReuniao(LocalTime.of(10, 30), LocalTime.of(12, 0));
        agenda4.fazerIntervalo(LocalTime.of(10, 45), LocalTime.of(11, 0));

        return new Object[][]{
            {agenda1, Duration.ofMinutes(240)},
            {agenda2, Duration.ofMinutes(300)},
            {agenda3, Duration.ofMinutes(300)},
            {agenda4, Duration.ofMinutes(240)}
        };

    }

    @Test(dataProvider = "tests")
    public void testDuracaoAtividades(Agenda dia, Duration duracaoEsperada) {
        assertEquals(duracaoEsperada, dia.getDuracaoAtividades());
    }

    @Test
    public void testDuracaoPorTipo() {
        Agenda agenda = new Agenda(Year.of(2014).atMonth(Month.MAY).atDay(20));

        agenda.desenvolver(LocalTime.of(13, 0), LocalTime.of(18, 0));
        agenda.concederSuporte(LocalTime.of(13, 0), LocalTime.of(15, 0));
        agenda.fazerIntervalo(LocalTime.of(14, 0), LocalTime.of(14, 15));
        agenda.concederSuporte(LocalTime.of(14, 15), LocalTime.of(16, 30));
        agenda.concederSuporte(LocalTime.of(17, 15), LocalTime.of(18, 0));

        assertEquals(Duration.ofMinutes(15), agenda.getDuracaoAtividades(Tipo.INTERVALO));
        assertEquals(Duration.ofMinutes(240), agenda.getDuracaoAtividades(Tipo.SUPORTE));
        assertEquals(Duration.ofMinutes(45), agenda.getDuracaoAtividades(Tipo.DESENVOLVIMENTO));
        assertEquals(Duration.ofMinutes(0), agenda.getDuracaoAtividades(Tipo.TELEFONE));
        assertEquals(Duration.ofMinutes(0), agenda.getDuracaoAtividades(Tipo.REUNIAO));
    }

}
