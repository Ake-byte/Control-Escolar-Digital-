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
        List<Role> filterByEdad = opcionEdades(filterBySemestre, informes);
        List<Role> filterByGenero = opcionGenero(filterByEdad, informes);
        List<Role> filterByNacionalidad = opcionNacionalidad(filterByGenero, informes);
        List<Role> filterByLugarNacimiento = opcionLugarNacimiento(filterByNacionalidad, informes);
        List<Role> filterByLenguaIndigena = opcionLenguaIndigena(filterByLugarNacimiento, informes);
        List<Role> filterByDiscapacidad = opcionDiscapacidad(filterByLenguaIndigena, informes);
        List<Role> filterByEnfermedadPermanente = opcionEnfermedadPermanente(filterByDiscapacidad, informes);
        List<Role> filterByEstatus = opcionEstatus(filterByEnfermedadPermanente, informes);
        List<Role> filterByBeca = opcionBecaConacyt(filterByEstatus, informes);

        List<Role> resultadoInforme = filterByBeca;

        return resultadoInforme;
    }

    private List<Role> opcionLenguaIndigena(List<Role> alumnos, Informes informes) {
        List<Role> resultado = new ArrayList<>();

        if(informes.getLenguaIndigena().equals("Si")){
            for (Role u : alumnos) {
                if (u.getUsers().getLenguaIndigena().equals(true))
                    resultado.add(u);
            }
        }
        else if(informes.getLenguaIndigena().equals("No")){
            for (Role u : alumnos) {
                if (u.getUsers().getLenguaIndigena().equals(false))
                    resultado.add(u);
            }
        }
        else if(informes.getLenguaIndigena().equals("Todo")){
            return alumnos;
        }

        return resultado;
    }

    private List<Role> opcionEnfermedadPermanente(List<Role> alumnos, Informes informes) {
        List<Role> resultado = new ArrayList<>();

        if(informes.getEnfermedadPermanente().equals("Si")){
            for (Role u : alumnos) {
                if (u.getUsers().getEnfermedadPermanente().equals(true))
                    resultado.add(u);
            }
        }
        else if(informes.getEnfermedadPermanente().equals("No")){
            for (Role u : alumnos) {
                if (u.getUsers().getEnfermedadPermanente().equals(false))
                    resultado.add(u);
            }
        }
        else if(informes.getEnfermedadPermanente().equals("Todo")){
            return alumnos;
        }

        return resultado;
    }

    private List<Role> opcionLugarNacimiento(List<Role> alumnos, Informes informes) {
        List<Role> resultado = new ArrayList<>();

        List<String> estados = getEstados();


            if(estados.contains(informes.getLugarNacimiento())){
                for (Role u : alumnos) {
                    if (u.getUsers().getEstadoNacimiento().equals(informes.getLugarNacimiento()))
                        resultado.add(u);
                }
            }

        else {
            if(informes.getLugarNacimiento().equals("Extranjero")){
                for (Role u : alumnos) {
                    if(!estados.contains(informes.getLugarNacimiento())){
                        resultado.add(u);
                    }
                }
                return resultado;
            }
            else if(informes.getLugarNacimiento().equals("Todo")){
                return alumnos;
            }
        }

        return resultado;
    }

    private List<String> getEstados() {
        List<String> estadosMex = new ArrayList<>();

        estadosMex.add("Aguascalientes");
        estadosMex.add("Baja California");
        estadosMex.add("Baja California Sur");
        estadosMex.add("Campeche");
        estadosMex.add("Chiapas");
        estadosMex.add("Chihuahua");
        estadosMex.add("Ciudad de México");
        estadosMex.add("Coahuila");
        estadosMex.add("Colima");
        estadosMex.add("Durango");
        estadosMex.add("Estado de México");
        estadosMex.add("Guanajuato");
        estadosMex.add("Guerrero");
        estadosMex.add("Hidalgo");
        estadosMex.add("Jalisco");
        estadosMex.add("Michoacán");
        estadosMex.add("Morelos");
        estadosMex.add("Nayarit");
        estadosMex.add("Nuevo León");
        estadosMex.add("Oaxaca");
        estadosMex.add("Puebla");
        estadosMex.add("Querétaro");
        estadosMex.add("Quintana Roo");
        estadosMex.add("San Luis Potosí");
        estadosMex.add("Sinaloa");
        estadosMex.add("Sonora");
        estadosMex.add("Tabasco");
        estadosMex.add("Tamaulipas");
        estadosMex.add("Tlaxcala");
        estadosMex.add("Veracruz");
        estadosMex.add("Yucatán");
        estadosMex.add("Zacatecas");

        return estadosMex;
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

        if(!informes.getEdadMin().equals("Todo") && !informes.getEdadMax().equals("Todo")){
            for (Role u : alumnos) {
                LocalDate birthday = convertDate(u.getUsers().getFechaNacimiento());
                if (Period.between(birthday, date).getYears() >= Integer.valueOf(informes.getEdadMin())
                        && Period.between(birthday, date).getYears() <= Integer.valueOf(informes.getEdadMax()))
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

        if(informes.getNacionalidad().equals("Mexicana")){
            for (Role u : alumnos) {
                if (u.getUsers().getPaisNacimiento().equals("Mexico"))
                    resultado.add(u);
            }
        }
        else if(informes.getNacionalidad().equals("Extranjera")){
            for (Role u : alumnos) {
                if (!u.getUsers().getPaisNacimiento().equals("Mexico"))
                    resultado.add(u);
            }
        }
        else if(informes.getNacionalidad().equals("Todo")){
            return alumnos;
        }

        return resultado;
    }

    public List<Role> opcionDiscapacidad(List<Role> alumnos, Informes informes){
        List<Role> resultado = new ArrayList<>();

        if(informes.getDiscapacidad().equals("Si")){
            for (Role u : alumnos) {
                if (u.getUsers().getDiscapacidad().equals(true))
                    resultado.add(u);
            }
        }
        else if(informes.getDiscapacidad().equals("No")){
            for (Role u : alumnos) {
                if (u.getUsers().getDiscapacidad().equals(false))
                    resultado.add(u);
            }
        }
        else if(informes.getDiscapacidad().equals("Todo")){
            return alumnos;
        }

        return resultado;
    }

    public List<Role> opcionEstatus(List<Role> alumnos, Informes informes){
        List<Role> resultado = new ArrayList<>();

        if(informes.getEstatus().equals("Inscritos")){
            for (Role u : alumnos) {
                if (u.getUsers().getEstatusEscolar().equals("Inscrito"))
                    resultado.add(u);
            }
        }
        else if(informes.getEstatus().equals("Egresados")){
            for (Role u : alumnos) {
                if (u.getUsers().getEstatusEscolar().equals("Egresado"))
                    resultado.add(u);
            }
        }
        else if(informes.getEstatus().equals("Todo")){
            return alumnos;
        }

        return resultado;
    }

    public List<Role> opcionBecaConacyt(List<Role> alumnos, Informes informes){
        List<Role> resultado = new ArrayList<>();

        if(informes.getBecaConacyt().equals("Con Beca")){
            for (Role u : alumnos) {
                if (u.getUsers().getBecaConacyt().equals(true))
                    resultado.add(u);
            }
        }
        else if(informes.getBecaConacyt().equals("Sin Beca")){
            for (Role u : alumnos) {
                if (u.getUsers().getBecaConacyt().equals(false))
                    resultado.add(u);
            }
        }
        else if(informes.getEstatus().equals("Todo")){
            return alumnos;
        }
        return resultado;
    }
}
