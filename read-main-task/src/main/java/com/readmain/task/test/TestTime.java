package com.readmain.task.test;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;

/**
 * Created by yuehao on 2017/9/18.
 */
public class TestTime {

    public static void main(String[] args) {

        LocalDate localDate = LocalDate.now();

        System.out.println(localDate.toString());
        System.out.println(localDate.toEpochDay());

        System.out.println(localDate.equals(LocalDate.now()));

        System.out.println(localDate.format(DateTimeFormatter.ISO_WEEK_DATE));
        System.out.println(localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(localDateTime.toString());

        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));


        System.out.println(localDate.plusYears(1).toString());


        System.out.println(localDate.minusDays(1).toString());

        System.out.println(localDate.toEpochDay() - localDate.minusDays(2).toEpochDay());


        System.out.println(localDate.toEpochDay());


        System.out.println(ChronoUnit.DAYS.between(localDate, localDate.minusDays(10)));


        System.out.println(ChronoUnit.MINUTES.between(localDateTime.minusMinutes(100), localDateTime));


        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        TemporalAccessor temporalAccessor = dateTimeFormatter.parse("2017-09-18 14:23:55");

        System.out.println(LocalDateTime.from(temporalAccessor).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
