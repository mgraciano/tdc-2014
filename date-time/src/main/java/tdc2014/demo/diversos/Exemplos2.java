package tdc2014.demo.diversos;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exemplos2 {

    public LocalDate getAmanha() {
        return LocalDate.now().plusDays(1);
    }

    public DayOfWeek getUltimoDiaDoMes() {
        return LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()).getDayOfWeek();
    }

    public long getTotalMesesAteONatal() {
        LocalDate dataAtual = LocalDate.now();
        LocalDate natal = LocalDate.of(2014, Month.DECEMBER, 25);
        
        return dataAtual.until(natal).toTotalMonths();
    }

    public long getTotalDiasAteOAnoNovo() {
        LocalDateTime dataAtual = LocalDateTime.now();
        LocalDateTime anoNovo = dataAtual.with(TemporalAdjusters.firstDayOfNextYear());
        return Duration.between(dataAtual, anoNovo).toMinutes();
    }

    public List<DayOfWeek> getUltimoDiaDosMesesEmUmAno(int valorAno) {
        Year ano = Year.of(valorAno);
        
//        List<DayOfWeek> diasDaSemana = new ArrayList<>();
//        for(Month mes : Month.values()){
//            DayOfWeek diaDaSemana =  ano.atMonth(mes).atEndOfMonth().getDayOfWeek();
//            
//            diasDaSemana.add(diaDaSemana);
//        }
        
//        return diasDaSemana;        
        
        return Stream.of(Month.values())
              .<DayOfWeek>map((mes) -> ano.atMonth(mes).atEndOfMonth().getDayOfWeek())
              .collect(Collectors.toList());        
    }
}
