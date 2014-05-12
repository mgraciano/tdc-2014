package tdc2014.temporal;

import tdc2014.temporal.UtilDaysAdjuster;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UtilDaysAdjusterTest {

    @DataProvider
    Object[][] tests() {
        Year year2014 = Year.of(2014);

        return new Object[][] {
            { year2014.atMonth(Month.MAY).atDay(1).with(new UtilDaysAdjuster(2)), year2014.atMonth(Month.MAY).atDay(5) },
            { year2014.atMonth(Month.MAY).atDay(2).with(new UtilDaysAdjuster()), year2014.atMonth(Month.MAY).atDay(2) },
            { year2014.atMonth(Month.MAY).atDay(2).with(new UtilDaysAdjuster(7)), year2014.atMonth(Month.MAY).atDay(12) },
            { year2014.atMonth(Month.JANUARY).atDay(1).with(new UtilDaysAdjuster(7)), year2014.atMonth(Month.JANUARY).atDay(13) },
            { year2014.atMonth(Month.MAY).atDay(3).with(new UtilDaysAdjuster()), year2014.atMonth(Month.MAY).atDay(5) }
        };

    }

    @Test(dataProvider = "tests")
    public void testDiaUtil(LocalDate data, LocalDate dataEsperada) {
        assertEquals(data, dataEsperada);
    }

}
