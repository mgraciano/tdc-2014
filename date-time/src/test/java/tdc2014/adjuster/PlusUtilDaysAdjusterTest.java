package tdc2014.adjuster;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PlusUtilDaysAdjusterTest {

    @DataProvider
    Object[][] tests() {
        Year ano = Year.of(2014);
        return new Object[][] {
            { ano.atMonth(Month.MAY).atDay(1).with(new PlusUtilDaysAdjuster(1)), ano.atMonth(Month.MAY).atDay(2) },
            { ano.atMonth(Month.MAY).atDay(2).with(new PlusUtilDaysAdjuster()), ano.atMonth(Month.MAY).atDay(2) },
            { ano.atMonth(Month.MAY).atDay(2).with(new PlusUtilDaysAdjuster(6)), ano.atMonth(Month.MAY).atDay(12) },
            { ano.atMonth(Month.MAY).atDay(3).with(new PlusUtilDaysAdjuster()), ano.atMonth(Month.MAY).atDay(5) }
        };

    }

    @Test(dataProvider = "tests")
    public void testDiaUtil(LocalDate data, LocalDate dataEsperada) {
        assertEquals(data, dataEsperada);
    }

}
