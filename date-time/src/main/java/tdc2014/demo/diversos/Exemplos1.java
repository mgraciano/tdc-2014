package tdc2014.demo.diversos;

import java.time.*;

public class Exemplos1 {

    //Criar uma data simples - lançamento do java 8 em - 18 de março de 2014
    public LocalDate getDataLancamentoJava8() {
        return LocalDate.of(2014, Month.MARCH, 18);
    }

    //Criar horário simples - 13:51
    public LocalTime getExemploHora() {
        return LocalTime.of(9, 33);
    }

    //Criar um exemplo de data e hora - 18 de março de 2014, 9:33
    public LocalDateTime getExemploDataHora() {
        return LocalDateTime.of(2014, Month.MARCH, 18, 9, 33);
    }

    //Criar um exemplo de data e hora - 18 de março de 2014, 9:33
    public LocalDateTime getExemploDataHoraComposta() {
        return LocalDateTime.of(getDataLancamentoJava8(), getExemploHora());
    }

    public LocalDate getDataCorrente() {
        return LocalDateTime.now().toLocalDate();
    }

}
