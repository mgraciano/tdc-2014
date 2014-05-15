package tdc2014.temporal;

import static org.testng.Assert.assertEquals;

import java.time.Month;
import java.time.Year;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DiasUteisPorMesAnoCounterTest {

    @DataProvider
    Object[][] tests() {
        Year year2014 = Year.of(2014);
        FeriadosQuery holidays = FeriadosFactory.create();

        final DiasUteisPorMesAnoCounter diasUteisCounter = new DiasUteisPorMesAnoCounter(holidays);

        return new Object[][] {
            { year2014.atMonth(Month.MAY).query(diasUteisCounter), 21l },
            { year2014.atMonth(Month.APRIL).query(diasUteisCounter), 20l },
            { year2014.atMonth(Month.JANUARY).query(diasUteisCounter), 22l }
        };

    }

    @Test(dataProvider = "tests")
    public void testDiaUtilCount(Long diasUteisCalculado, Long diasUteisEsperado) {
        assertEquals(diasUteisCalculado, diasUteisEsperado);
    }

}
