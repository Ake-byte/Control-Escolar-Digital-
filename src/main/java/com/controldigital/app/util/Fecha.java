package com.controldigital.app.util;

import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Fecha {

    public static LocalDate convertDate(Date date) {
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static LocalDate currentDate(){
        Date input = new Date();
        Instant instant = input.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate date = zdt.toLocalDate();

        return date;
    }

    public static List<Month> mesesA(){

        List<Month> meses = new ArrayList<>();

        meses.add(Month.JULY);
        meses.add(Month.AUGUST);
        meses.add(Month.SEPTEMBER);
        meses.add(Month.OCTOBER);
        meses.add(Month.NOVEMBER);
        meses.add(Month.DECEMBER);

        return meses;
    }

    public static List<Month> mesesB(){

        List<Month> meses = new ArrayList<>();

        meses.add(Month.JANUARY);
        meses.add(Month.FEBRUARY);
        meses.add(Month.MARCH);
        meses.add(Month.APRIL);
        meses.add(Month.MAY);
        meses.add(Month.JUNE);

        return meses;
    }
}
