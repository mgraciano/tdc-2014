package tdc2014.demo.timesheet;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tdc2014.demo.timesheet.Atividade.Tipo;

public class AgendaDiariaSumarizadaTest {

    @DataProvider
    Object[][] testSumarizacaoAtividades() {
        final LocalDate dataBase = LocalDate.of(2014, Month.APRIL, 21);

        final List<Atividade> agendaDiaria = new ArrayList<>();
        agendaDiaria.add(new Atividade(Tipo.DESENVOLVIMENTO, dataBase.atTime(8, 30), dataBase.atTime(12, 0)));
        agendaDiaria.add(new Atividade(Tipo.ADMINISTRATIVA, dataBase.atTime(9, 30), dataBase.atTime(10, 0)));
        agendaDiaria.add(new Atividade(Tipo.LEGAL, dataBase.atTime(10, 0), dataBase.atTime(10, 30)));

        return new Object[][] {
            { agendaDiaria }
        };
    }

    @Test(dataProvider = "testSumarizacaoAtividades")
    public void testSumarizacaoAtividades(final List<Atividade> agendaDiaria) {
        final AgendaDiariaSumarizada agendaSumarizada = new AgendaDiariaSumarizada(agendaDiaria);

        final Iterator<AtividadeSumarizada> iterator = agendaSumarizada.getAgendaDiariaSumarizada().iterator();
        assertEquals(iterator.next().getDuracao(), Duration.ofHours(2).plusMinutes(30));
        assertEquals(iterator.next().getDuracao(), Duration.ofMinutes(30));
        assertEquals(iterator.next().getDuracao(), Duration.ofMinutes(30));
    }

}
