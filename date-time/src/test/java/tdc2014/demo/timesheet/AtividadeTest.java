package tdc2014.demo.timesheet;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AtividadeTest {

    @DataProvider
    Object[][] testGetDuracaoBruta() {
        final LocalDate dataBase = LocalDate.of(2014, Month.APRIL, 20);

        return new Object[][] {
            { new Atividade(null, dataBase.atTime(8, 30), dataBase.atTime(LocalTime.NOON)), Duration.ofHours(3)
                .plusMinutes(30) },
            { new Atividade(null, dataBase.atTime(13, 30), dataBase.atTime(18, 0)), Duration.ofHours(4).plusMinutes(30) },
            { new Atividade(null, dataBase.atTime(8, 30), dataBase.atTime(18, 0)), Duration.ofHours(9).plusMinutes(30) }
        };
    }

    @Test(dataProvider = "testGetDuracaoBruta")
    public void testGetDuracaoBruta(final Atividade atividade, final Duration expected) {
        assertEquals(atividade.getDuracaoBruta(), expected);
    }

}
