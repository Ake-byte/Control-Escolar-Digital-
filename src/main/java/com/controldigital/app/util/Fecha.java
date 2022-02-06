package com.controldigital.app.util;

import com.controldigital.app.models.entity.Expediente;
import com.controldigital.app.models.entity.Usuario;
import com.controldigital.app.service.IExpedienteService;
import com.controldigital.app.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Fecha {

    @Autowired
    private IUsuarioService iUsuarioService;

    @Autowired
    private IExpedienteService expedienteService;

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
