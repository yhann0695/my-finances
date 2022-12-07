package br.com.investimento.financas.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    
    public static  boolean isMesAno(LocalDate data) {
        return data.getMonthValue() == LocalDate.now().getMonthValue() && data.getYear() == LocalDate.now().getYear();
    }

    public static boolean isMesTrimestre(LocalDate data) {
        return data.getYear() == LocalDate.now().getYear() && data.getMonthValue() % 3 == 0;
    }

    public static boolean isMesSemestre(LocalDate data) {
        return data.getYear() == LocalDate.now().getYear() && data.getMonthValue() % 6 == 0;
    }

    public static boolean isMesAnterior(LocalDate data) {
        return data.getMonthValue() < LocalDate.now().getMonthValue() && data.getYear() == LocalDate.now().getYear();
    }

    public static boolean isSemena(LocalDate data) {
        return data.getYear() == LocalDate.now().getYear() && data.getMonthValue() % 7 == 0;
    }

    public static boolean isUltimoDiaSemana(LocalDate data) {
        return data.getYear() == LocalDate.now().getYear() && data.getMonthValue() % 7 == 0 && data.getDayOfMonth() == LocalDate.now().getDayOfMonth();
    }

    public static boolean isUltimoDiaMes(LocalDate data) {
        return LocalDate.now().withMonth(data.getMonthValue()).with(TemporalAdjusters.lastDayOfMonth()) == data;
    }

    public static boolean isUltimoDiaDoMesEHora(LocalDateTime data) {
        return LocalDateTime.now()
                            .withMonth(data.getMonthValue())
                            .with(TemporalAdjusters.lastDayOfMonth()).equals(data) 
                            && LocalDateTime.now().getHour() == 23;
    }

    public static boolean primeiroUltimoDiaMesPassado(LocalDate data) {
        return LocalDate.now().withMonth(data.getMonthValue()).with(TemporalAdjusters.firstDayOfMonth()) == data && LocalDate.now().withMonth(data.getMonthValue()).with(TemporalAdjusters.lastDayOfMonth()) == data;
    }

    public static LocalDate buscarMesPassado() {
        return LocalDate.now().minusMonths(1);
    }

    public static Date convertLocalDateToDate(LocalDate data) {
        return Date.from(data.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate convertDateToLocalDate(Date data) {
        return LocalDate.from(data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    public static String formatarData(LocalDate data) {
        Locale local = new Locale("pt","BR");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM 'de' yyyy", local);
        return formatter.format(data);
    }
}