package tdc2014.temporal;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DiaPagamentoAdjusterTest {

    @DataProvider
    Object[][] tests() {
        final Year year2014 = Year.of(2014);
        final YearMonth maio2014 = year2014.atMonth(Month.MAY);
        final YearMonth junho2014 = year2014.atMonth(Month.JUNE);

        final DiaPagamentoAdjuster diaPagamentoAdjuster = new DiaPagamentoAdjuster();

        return new Object[][] {
            { maio2014.atDay(2).with(diaPagamentoAdjuster), maio2014.atDay(8) },
            { maio2014.atDay(10).with(diaPagamentoAdjuster), junho2014.atDay(6) },
            { junho2014.atDay(3).with(diaPagamentoAdjuster), junho2014.atDay(6) }
        };

    }

    @Test(dataProvider = "tests")
    public void testDiaUtil(LocalDate data, LocalDate dataEsperada) {
        assertEquals(data, dataEsperada);
    }
}
