package tdc2014.demo.diversos;

import org.testng.annotations.Test;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;

import org.testng.annotations.BeforeTest;

public class TestExemplos1 {

    private Exemplos1 exemplos1;

    @BeforeTest
    public void before() {
        exemplos1 = new Exemplos1();
    }

    @Test
    public void testDataLancamentoJava8() {
        final LocalDate lancamentoJava8 = exemplos1.getDataLancamentoJava8();
        assertEquals(2014, lancamentoJava8.getYear());
        assertEquals(18, lancamentoJava8.getDayOfMonth());
        assertEquals(Month.MARCH, lancamentoJava8.getMonth());
    }

    @Test
    public void testHora09_33() {
        final LocalTime localTime = exemplos1.getExemploHora();
        assertEquals(9, localTime.getHour());
        assertEquals(33, localTime.getMinute());
        assertEquals(0, localTime.getSecond());
    }

    @Test
    public void testDataLancamentoJava8EHora() {
        final LocalDateTime combinacao = exemplos1.getExemploDataHora();
        testDataHoraComposicao(combinacao);
    }

    @Test
    public void testDataHoraComposta() {
        testDataHoraComposicao(exemplos1.getExemploDataHoraComposta());
    }

    @Test
    public void testDataCorrente() {
        assertEquals(LocalDate.now(), exemplos1.getDataCorrente());
    }

    private void testDataHoraComposicao(LocalDateTime combinacao) {
        assertEquals(2014, combinacao.getYear());
        assertEquals(18, combinacao.getDayOfMonth());
        assertEquals(Month.MARCH, combinacao.getMonth());
        assertEquals(9, combinacao.getHour());
        assertEquals(33, combinacao.getMinute());
        assertEquals(0, combinacao.getSecond());
    }

    @Test
    public void testAmanha() {
        assertEquals(LocalDate.now().plusDays(1), exemplos1.getAmanha());
    }
    
}