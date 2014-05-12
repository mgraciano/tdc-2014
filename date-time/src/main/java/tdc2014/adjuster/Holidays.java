package tdc2014.adjuster;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;

public class Holidays {

    private Set<LocalDate> holidays;

    public Holidays(Set<LocalDate> holidays) {
        this.holidays = holidays;
    }

    public Holidays() {
        this(getHolidays());
    }

    public boolean isHoliday(LocalDate date) {
        return holidays.contains(date);
    }

    private static Set<LocalDate> getHolidays() {
        Year year = Year.of(2014);
        Set<LocalDate> holidays = new HashSet<>();

        holidays.add(year.atMonth(Month.JANUARY).atDay(1));
        holidays.add(year.atMonth(Month.JANUARY).atDay(6));
        holidays.add(year.atMonth(Month.MARCH).atDay(4));
        holidays.add(year.atMonth(Month.MARCH).atDay(5));
        holidays.add(year.atMonth(Month.APRIL).atDay(18));
        holidays.add(year.atMonth(Month.APRIL).atDay(20));
        holidays.add(year.atMonth(Month.APRIL).atDay(21));
        holidays.add(year.atMonth(Month.MAY).atDay(1));
        holidays.add(year.atMonth(Month.JUNE).atDay(19));
        holidays.add(year.atMonth(Month.SEPTEMBER).atDay(7));
        holidays.add(year.atMonth(Month.OCTOBER).atDay(12));
        holidays.add(year.atMonth(Month.NOVEMBER).atDay(2));
        holidays.add(year.atMonth(Month.NOVEMBER).atDay(15));
        holidays.add(year.atMonth(Month.DECEMBER).atDay(25));

        return holidays;
    }
}
