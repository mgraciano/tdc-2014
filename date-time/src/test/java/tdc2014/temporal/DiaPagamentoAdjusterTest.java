package tdc2014.temporal;



import static org.testng.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DiaPagamentoAdjusterTest {
@DataProvider
    Object[][] tests() {
        Year year2014 = Year.of(2014);
        FeriadosQuery holidays = FeriadosFactory.create();
        DiaPagamentoAdjuster payDayAdjuster = new DiaPagamentoAdjuster(holidays);
        return new Object[][] {
            { year2014.atMonth(Month.MAY).atDay(2).with(payDayAdjuster), year2014.atMonth(Month.MAY).atDay(8) },
            { year2014.atMonth(Month.MAY).atDay(10).with(payDayAdjuster), year2014.atMonth(Month.JUNE).atDay(6) },
            { year2014.atMonth(Month.JUNE).atDay(3).with(payDayAdjuster), year2014.atMonth(Month.JUNE).atDay(6) }
        };

    }

    @Test(dataProvider = "tests")
    public void testDiaUtil(LocalDate data, LocalDate dataEsperada) {
        assertEquals(data, dataEsperada);
    }    
}
