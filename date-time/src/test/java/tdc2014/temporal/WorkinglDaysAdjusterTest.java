package tdc2014.temporal;

import tdc2014.temporal.WorkinglDaysAdjuster;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WorkinglDaysAdjusterTest {

    @DataProvider
    Object[][] tests() {
        Year year2014 = Year.of(2014);

        Holidays holidays = HolidaysFactory.create();
        return new Object[][] {
            { year2014.atMonth(Month.MAY).atDay(1).with(new WorkinglDaysAdjuster(2, holidays)), year2014.atMonth(Month.MAY).atDay(5) },
            { year2014.atMonth(Month.MAY).atDay(2).with(new WorkinglDaysAdjuster(holidays)), year2014.atMonth(Month.MAY).atDay(2) },
            { year2014.atMonth(Month.MAY).atDay(2).with(new WorkinglDaysAdjuster(7, holidays)), year2014.atMonth(Month.MAY).atDay(12) },
            { year2014.atMonth(Month.JANUARY).atDay(1).with(new WorkinglDaysAdjuster(7, holidays)), year2014.atMonth(Month.JANUARY).atDay(13) },
            { year2014.atMonth(Month.MAY).atDay(3).with(new WorkinglDaysAdjuster(holidays)), year2014.atMonth(Month.MAY).atDay(5) }
        };

    }

    @Test(dataProvider = "tests")
    public void testDiaUtil(LocalDate data, LocalDate dataEsperada) {
        assertEquals(data, dataEsperada);
    }

}
