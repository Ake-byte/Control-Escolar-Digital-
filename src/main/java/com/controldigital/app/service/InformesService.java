package com.controldigital.app.service;

import com.controldigital.app.models.entity.Role;
import com.controldigital.app.util.Informes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.controldigital.app.controllers.AdminController.convertDate;

@Service
public class InformesService implements IInformesService{

    @Autowired
    private IRoleService roleService;

    @Override
    public List<Role> findAlumnosBy(Informes informes) {

        List<Role> alumnos = roleService.findUsuarioByRole("ROLE_USER2");
        List<Role> filterByPosgrado = opcionPosgrado(alumnos, informes);
        List<Role> filterBySemestre = opcionSemestre(filterByPosgrado, informes);
        List<Role> filterByEdad= opcionEdades(filterBySemestre, informes);
        List<Role> filterByGenero = opcionGenero(filterByEdad, informes);
        //List<Role> filterByNacionalidad = opcionNacionalidad(filterByGenero, informes);
        //List<Role> filterByDiscapcidad = opcionDiscapacidad(filterByNacionalidad, informes);
        //List<Role> filterByEstatus = opcionEstatus(filterByDiscapcidad, informes);
        //List<Role> filterByBeca = opcionBecaConacyt(filterByEstatus, informes);

        //List<Role> resultadoInforme = filterByBeca;
        List<Role> resultadoInforme = filterByGenero;

        return resultadoInforme;
    }

    public List<Role> opcionPosgrado(List<Role> alumnos, Informes informes){

        List<Role> resultado = new ArrayList<>();


        if(informes.getPosgrado().equals("Maestría")){
            for (Role u : alumnos) {
                if (u.getUsers().getGrado().equals("Maestría"))
                    resultado.add(u);
            }
        }
        else if(informes.getPosgrado().equals("Doctorado")){
            for (Role u : alumnos) {
                if (u.getUsers().getGrado().equals("Doctorado"))
                    resultado.add(u);
            }
        }
        else if(informes.getPosgrado().equals("Todo")){
            return alumnos;
        }

        return resultado;
    }

    public List<Role> opcionSemestre(List<Role> alumnos, Informes informes){

        List<Role> resultado = new ArrayList<>();

        if(!informes.getSemestre().equals("Todo")){
            for (Role u : alumnos) {
                if (u.getUsers().getSemestre().equals(informes.getSemestre()))
                    resultado.add(u);
            }
        }
        else {
            return alumnos;
        }

        return resultado;
    }

    public List<Role> opcionEdades(List<Role> alumnos, Informes informes){

        List<Role> resultado = new ArrayList<>();

        Date input = new Date();
        Instant instant = input.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate date = zdt.toLocalDate();

        if(!informes.getEdades().equals("Todo")){
            for (Role u : alumnos) {
                LocalDate birthday = convertDate(u.getUsers().getFechaNacimiento());
                if (String.valueOf(Period.between(birthday, date).getYears()) == informes.getEdades())
                    resultado.add(u);
            }
        }
        else {
            return alumnos;
        }

        return resultado;
    }

    public List<Role> opcionGenero(List<Role> alumnos, Informes informes){
        List<Role> resultado = new ArrayList<>();

        if(informes.getGenero().equals("Hombre")){
            for (Role u : alumnos) {
                if (u.getUsers().getGenero().equals("Hombre"))
                    resultado.add(u);
            }
        }
        else if(informes.getGenero().equals("Mujer")){
            for (Role u : alumnos) {
                if (u.getUsers().getGenero().equals("Mujer"))
                    resultado.add(u);
            }
        }
        else if(informes.getGenero().equals("Otro")){
            for (Role u : alumnos) {
                if (u.getUsers().getGenero().equals("Otro"))
                    resultado.add(u);
            }
        }
        else if(informes.getGenero().equals("Todo")){
            return alumnos;
        }

        return resultado;
    }

    public List<Role> opcionNacionalidad(List<Role> alumnos, Informes informes){
        List<Role> resultado = new ArrayList<>();

        return resultado;
    }

    public List<Role> opcionDiscapacidad(List<Role> alumnos, Informes informes){
        List<Role> resultado = new ArrayList<>();

        return resultado;
    }

    public List<Role> opcionEstatus(List<Role> alumnos, Informes informes){
        List<Role> resultado = new ArrayList<>();

        return resultado;
    }

    public List<Role> opcionBecaConacyt(List<Role> alumnos, Informes informes){
        List<Role> resultado = new ArrayList<>();

        return resultado;
    }
}
