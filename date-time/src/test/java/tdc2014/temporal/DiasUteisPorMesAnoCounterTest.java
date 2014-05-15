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
        
        DiasUteisPorMesAnoCounter countQuery = new DiasUteisPorMesAnoCounter(holidays);
        
        return new Object[][] {
            { year2014.atMonth(Month.MAY).query(countQuery), 21 },
            { year2014.atMonth(Month.APRIL).query(countQuery), 20 },
            { year2014.atMonth(Month.JANUARY).query(countQuery), 22 }
        };

    }

    @Test(dataProvider = "tests")
    public void testDiaUtilCount(Integer diasUteisCalculado, Integer diasUteisEsperado) {
        assertEquals(diasUteisCalculado, diasUteisEsperado);
    }

}
