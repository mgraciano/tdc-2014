package tdc2014.demo.diversos;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

public class TestExemplos2 {

    private Exemplos2 exemplos2;

    @BeforeTest
    public void before() {
        this.exemplos2 = new Exemplos2();
    }

    @Test
    public void getUltimoDiaDoMes() {
        assertEquals(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()).getDayOfWeek(), exemplos2.getUltimoDiaDoMes());
    }

    @Test
    public void testTotalMesesAteONatal() {
        LocalDate dataAtual = LocalDate.now();
        LocalDate natal = LocalDate.of(2014, Month.DECEMBER, 25);
        
        assertEquals(dataAtual.until(natal).toTotalMonths(), exemplos2.getTotalMesesAteONatal());
    }

    @Test
    public void testTotalMinutosAteOAnoNovo() {
        LocalDateTime dataAtual = LocalDateTime.now();
        LocalDateTime anoNovo = dataAtual.with(TemporalAdjusters.firstDayOfNextYear());
        long totalDiasAteOAnoNovo = Duration.between(dataAtual, anoNovo).toMinutes();
        
        assertEquals(totalDiasAteOAnoNovo, exemplos2.getTotalDiasAteOAnoNovo());
    }

    @Test
    public void testUltimoDiaDosMesesEmUmAno() {
        List<DayOfWeek> dias = exemplos2.getUltimoDiaDosMesesEmUmAno(2014);

        dias.stream().forEach(day -> System.out.println(day));

        List<DayOfWeek> diasEsperados = Arrays.asList(
                new DayOfWeek[]{DayOfWeek.valueOf("FRIDAY"),
                        DayOfWeek.valueOf("FRIDAY"),
                        DayOfWeek.valueOf("MONDAY"),
                        DayOfWeek.valueOf("WEDNESDAY"),
                        DayOfWeek.valueOf("SATURDAY"),
                        DayOfWeek.valueOf("MONDAY"),
                        DayOfWeek.valueOf("THURSDAY"),
                        DayOfWeek.valueOf("SUNDAY"),
                        DayOfWeek.valueOf("TUESDAY"),
                        DayOfWeek.valueOf("FRIDAY"),
                        DayOfWeek.valueOf("SUNDAY"),
                        DayOfWeek.valueOf("WEDNESDAY")
        });

        assertEquals(diasEsperados, dias);
    }
}
