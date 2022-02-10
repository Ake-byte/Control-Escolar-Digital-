package com.controldigital.app;

import com.controldigital.app.models.entity.Expediente;
import com.controldigital.app.service.IExpedienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CambiarSemestre {
    @Autowired
    private IExpedienteService expedienteService;

    /** La notación @Scheduled de Spring, permite automatizar tareas. En este sistema se utiliza para actualizar el semestre en curso de los alumnos inscritos.
     * Siendo las funciones:
     *              -cambiarSemestreA de Julio a Diciembre
     *              -cambiarSemestreB de Enero a Junio
     * El cambio se efectúa el día 31 del primer mes (Julio para el semestre A y Enero para el semestre B) a la medianoche.
     *
     * PARÁMETROS DE LA FUNCIÓN:
     *
     *               ┌───────────── second (0-59)
     *               │ ┌───────────── minute (0 - 59)
     *               │ │ ┌───────────── hour (0 - 23)
     *               │ │ │ ┌───────────── day of the month (1 - 31)
     *               │ │ │ │ ┌───────────── month (1 - 12) (or JAN-DEC)
     *               │ │ │ │ │ ┌───────────── day of the week (0 - 7)
     *               │ │ │ │ │ │          (or MON-SUN -- 0 or 7 is Sunday)
     *               │ │ │ │ │ │
     *               * * * * * *
     */
    @Bean
    //@Scheduled(cron="0 0 0 31 7 ?")
    @Scheduled(cron="0 0/2 * * * *")
    public void cambiarsemestreA(){
        List<Expediente> alumnosInscritos = expedienteService.findAll().stream()
                .filter(e -> e.getEstatusEscolar() != null && e.getEstatusEscolar().equals("Inscrito")).collect(Collectors.toList());

        for(Expediente e: alumnosInscritos){
            e.setNumSemestre(e.getNumSemestre() + 1);
            if(e.getGrado().equals("Maestría") && e.getNumSemestre() > 4)
                e.setEstatusEscolar("Egresado");
            if(e.getGrado().equals("Doctorado") && e.getNumSemestre() > 8)
                e.setEstatusEscolar("Egresado");
            expedienteService.save(e);
        }
    }

    @Bean
    @Scheduled(cron="0 0 0 31 1 ?")
    public void cambiarsemestreB(){
        List<Expediente> alumnosInscritos = expedienteService.findAll().stream()
                .filter(e -> e.getEstatusEscolar() != null && e.getEstatusEscolar().equals("Inscrito")).collect(Collectors.toList());

        for(Expediente e: alumnosInscritos){
            e.setNumSemestre(e.getNumSemestre() + 1);
            if(e.getGrado().equals("Maestría") && e.getNumSemestre() > 4)
                e.setEstatusEscolar("Egresado");
            if(e.getGrado().equals("Doctorado") && e.getNumSemestre() > 8)
                e.setEstatusEscolar("Egresado");
            expedienteService.save(e);
        }
    }
}