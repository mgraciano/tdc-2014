package tdc2014.adjuster;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PayDayAdjusterTest {
@DataProvider
    Object[][] tests() {
        Year ano = Year.of(2014);
        PayDayAdjuster payDayAdjuster = new PayDayAdjuster();
        return new Object[][] {
            { ano.atMonth(Month.MAY).atDay(2).with(payDayAdjuster), ano.atMonth(Month.MAY).atDay(7) },
            { ano.atMonth(Month.MAY).atDay(10).with(payDayAdjuster), ano.atMonth(Month.JUNE).atDay(6) },
            { ano.atMonth(Month.JUNE).atDay(3).with(payDayAdjuster), ano.atMonth(Month.JUNE).atDay(6) }
        };

    }

    @Test(dataProvider = "tests")
    public void testDiaUtil(LocalDate data, LocalDate dataEsperada) {
        assertEquals(data, dataEsperada);
    }    
}
