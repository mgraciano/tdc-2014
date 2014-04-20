package tdc2014.testabilidade;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PastValidatorForLocalDateTestableTest {

    private static final Instant agora = LocalDateTime.of(2014, Month.MAY, 20, 14, 10).toInstant(ZoneOffset.ofHours(-3));
    private static final Clock relogioAgora = Clock.fixed(agora, ZoneId.systemDefault());

    @DataProvider
    Object[][] testisValid() {
        return new Object[][] {
            { LocalDate.of(2014, Month.MAY, 18), true },
            { LocalDate.of(2014, Month.MAY, 19), true },
            { LocalDate.of(2014, Month.MAY, 20), false },
            { LocalDate.of(2014, Month.MAY, 21), false }
        };
    }

    @Test(dataProvider = "testisValid")
    public void testisValid(final LocalDate date, final boolean expected) {
        final PastValidatorForLocalDateTestable validation = new PastValidatorForLocalDateTestable(relogioAgora);
        assertEquals(validation.isValid(date, null), expected);
    }

}
