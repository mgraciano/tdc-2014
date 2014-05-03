package tdc2014.demo.timesheet;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
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

public class SumarizacaoTest {

    @DataProvider
    Object[][] testDuracoesLiquidas() {
        final LocalDate dataBase = LocalDate.of(2014, Month.APRIL, 21);

        final List<Atividade> agendaDiaria = new ArrayList<>();
        agendaDiaria.add(new Atividade(Tipo.DESENVOLVIMENTO, dataBase.atTime(8, 30), dataBase.atTime(12, 0)));
        agendaDiaria.add(new Atividade(Tipo.ADMINISTRATIVA, dataBase.atTime(9, 30), dataBase.atTime(10, 0)));
        agendaDiaria.add(new Atividade(Tipo.LEGAL, dataBase.atTime(10, 0), dataBase.atTime(10, 30)));

        return new Object[][] {
            { agendaDiaria }
        };
    }

    @Test(dataProvider = "testDuracoesLiquidas")
    public void testDuracoesLiquidas(final List<Atividade> agendaDiaria) {

        final List<AtividadeSumarizada> agendaSumarizada = agendaDiaria.stream()
                .map(atividade -> {
                    final Duration duracaoAtividadesIntercaladas = agendaDiaria.stream()
                    .filter(intercalada -> intercalada.getHorarioInicio().isAfter(atividade.getHorarioInicio())
                            && intercalada.getHorarioInicio().isBefore(atividade.getHorarioTermino()))
                    .reduce(Duration.ZERO,
                            (duracao, intercalada) -> duracao.plus(intercalada.getDuracaoBruta()),
                            (left, right) -> left.plus(right));
                    return new AtividadeSumarizada(atividade.getTipo(), atividade.getHorarioInicio(),
                            atividade.getHorarioTermino(), atividade.getDuracaoBruta().minus(
                                    duracaoAtividadesIntercaladas));
                })
                .sorted(comparing(AtividadeSumarizada::getHorarioInicio))
                .collect(toList());

        final Iterator<AtividadeSumarizada> iterator = agendaSumarizada.iterator();
        assertEquals(iterator.next().getDuracao(), Duration.ofHours(2).plusMinutes(30));
        assertEquals(iterator.next().getDuracao(), Duration.ofMinutes(30));
        assertEquals(iterator.next().getDuracao(), Duration.ofMinutes(30));
    }

}
