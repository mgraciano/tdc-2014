package tdc2014.temporal;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DiasUteisAdjusterTest {

    @DataProvider
    Object[][] tests() {
        final Year year2014 = Year.of(2014);
        final YearMonth janeiro2014 = year2014.atMonth(Month.JANUARY);
        final YearMonth maio2014 = year2014.atMonth(Month.MAY);

        return new Object[][] {
            { maio2014.atDay(1).with(new DiasUteisAdjuster(2)), maio2014.atDay(5) },
            { maio2014.atDay(2).with(new DiasUteisAdjuster()), maio2014.atDay(2) },
            { maio2014.atDay(2).with(new DiasUteisAdjuster(7)), maio2014.atDay(12) },
            { janeiro2014.atDay(1).with(new DiasUteisAdjuster(7)), janeiro2014.atDay(10) },
            { maio2014.atDay(3).with(new DiasUteisAdjuster()), maio2014.atDay(5) }
        };

    }

    @Test(dataProvider = "tests")
    public void testDiaUtil(LocalDate data, LocalDate dataEsperada) {
        assertEquals(data, dataEsperada);
    }

}
