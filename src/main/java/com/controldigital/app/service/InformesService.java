package com.controldigital.app.service;

import com.controldigital.app.models.entity.Usuario;
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
    private UsuarioService usuarioService;

    @Override
    public List<Usuario> findAlumnosBy(Informes informes) {

        List<Usuario> alumnos = usuarioService.findUserByRole("ROLE_USER2");
        List<Usuario> filterByPosgrado = opcionPosgrado(alumnos, informes);
        List<Usuario> filterBySemestre = opcionSemestre(filterByPosgrado, informes);
        List<Usuario> filterByEdad = opcionEdades(filterBySemestre, informes);
        List<Usuario> filterByGenero = opcionGenero(filterByEdad, informes);
        List<Usuario> filterByNacionalidad = opcionNacionalidad(filterByGenero, informes);
        List<Usuario> filterByLugarNacimiento = opcionLugarNacimiento(filterByNacionalidad, informes);
        List<Usuario> filterByLenguaIndigena = opcionLenguaIndigena(filterByLugarNacimiento, informes);
        List<Usuario> filterByDiscapacidad = opcionDiscapacidad(filterByLenguaIndigena, informes);
        List<Usuario> filterByEnfermedadPermanente = opcionEnfermedadPermanente(filterByDiscapacidad, informes);
        List<Usuario> filterByEstatus = opcionEstatus(filterByEnfermedadPermanente, informes);
        List<Usuario> filterByBeca = opcionBecaConacyt(filterByEstatus, informes);

        List<Usuario> resultadoInforme = filterByBeca;

        return resultadoInforme;
    }

    private List<Usuario> opcionLenguaIndigena(List<Usuario> alumnos, Informes informes) {
        List<Usuario> resultado = new ArrayList<>();

        if(informes.getLenguaIndigena().equals("Si")){
            for (Usuario u : alumnos) {
                if (u.getInfoPersonal().getLenguaIndigena().equals(true))
                    resultado.add(u);
            }
        }
        else if(informes.getLenguaIndigena().equals("No")){
            for (Usuario u : alumnos) {
                if (u.getInfoPersonal().getLenguaIndigena().equals(false))
                    resultado.add(u);
            }
        }
        else if(informes.getLenguaIndigena().equals("Todo")){
            return alumnos;
        }

        return resultado;
    }

    private List<Usuario> opcionEnfermedadPermanente(List<Usuario> alumnos, Informes informes) {
        List<Usuario> resultado = new ArrayList<>();

        if(informes.getEnfermedadPermanente().equals("Si")){
            for (Usuario u : alumnos) {
                if (u.getInfoPersonal().getEnfermedadPermanente().equals(true))
                    resultado.add(u);
            }
        }
        else if(informes.getEnfermedadPermanente().equals("No")){
            for (Usuario u : alumnos) {
                if (u.getInfoPersonal().getEnfermedadPermanente().equals(false))
                    resultado.add(u);
            }
        }
        else if(informes.getEnfermedadPermanente().equals("Todo")){
            return alumnos;
        }

        return resultado;
    }

    private List<Usuario> opcionLugarNacimiento(List<Usuario> alumnos, Informes informes) {
        List<Usuario> resultado = new ArrayList<>();

        List<String> estados = getEstados();


            if(estados.contains(informes.getLugarNacimiento())){
                for (Usuario u : alumnos) {
                    if (u.getInfoPersonal().getEstadoNacimiento().equals(informes.getLugarNacimiento()))
                        resultado.add(u);
                }
            }

        else {
            if(informes.getLugarNacimiento().equals("Extranjero")){
                for (Usuario u : alumnos) {
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

    public List<Usuario> opcionPosgrado(List<Usuario> alumnos, Informes informes){

        List<Usuario> resultado = new ArrayList<>();


        if(informes.getPosgrado().equals("Maestría")){
            for (Usuario u : alumnos) {
                if (u.getExpediente().getGrado().equals("Maestría"))
                    resultado.add(u);
            }
        }
        else if(informes.getPosgrado().equals("Doctorado")){
            for (Usuario u : alumnos) {
                if (u.getExpediente().getGrado().equals("Doctorado"))
                    resultado.add(u);
            }
        }
        else if(informes.getPosgrado().equals("Todo")){
            return alumnos;
        }

        return resultado;
    }

    public List<Usuario> opcionSemestre(List<Usuario> alumnos, Informes informes){

        List<Usuario> resultado = new ArrayList<>();

        if(!informes.getSemestre().equals("Todo")){
            for (Usuario u : alumnos) {
                if (u.getExpediente().getSemestre().equals(informes.getSemestre()))
                    resultado.add(u);
            }
        }
        else {
            return alumnos;
        }

        return resultado;
    }

    public List<Usuario> opcionEdades(List<Usuario> alumnos, Informes informes){

        List<Usuario> resultado = new ArrayList<>();

        Date input = new Date();
        Instant instant = input.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate date = zdt.toLocalDate();

        if(!informes.getEdadMin().equals("Todo") && !informes.getEdadMax().equals("Todo")){
            for (Usuario u : alumnos) {
                LocalDate birthday = convertDate(u.getInfoPersonal().getFechaNacimiento());
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

    public List<Usuario> opcionGenero(List<Usuario> alumnos, Informes informes){
        List<Usuario> resultado = new ArrayList<>();

        if(informes.getGenero().equals("Hombre")){
            for (Usuario u : alumnos) {
                if (u.getInfoPersonal().getGenero().equals("Hombre"))
                    resultado.add(u);
            }
        }
        else if(informes.getGenero().equals("Mujer")){
            for (Usuario u : alumnos) {
                if (u.getInfoPersonal().getGenero().equals("Mujer"))
                    resultado.add(u);
            }
        }
        else if(informes.getGenero().equals("Otro")){
            for (Usuario u : alumnos) {
                if (u.getInfoPersonal().getGenero().equals("Otro"))
                    resultado.add(u);
            }
        }
        else if(informes.getGenero().equals("Todo")){
            return alumnos;
        }

        return resultado;
    }

    public List<Usuario> opcionNacionalidad(List<Usuario> alumnos, Informes informes){
        List<Usuario> resultado = new ArrayList<>();

        if(informes.getNacionalidad().equals("Mexicana")){
            for (Usuario u : alumnos) {
                if (u.getInfoPersonal().getPaisNacimiento().equals("Mexico"))
                    resultado.add(u);
            }
        }
        else if(informes.getNacionalidad().equals("Extranjera")){
            for (Usuario u : alumnos) {
                if (!u.getInfoPersonal().getPaisNacimiento().equals("Mexico"))
                    resultado.add(u);
            }
        }
        else if(informes.getNacionalidad().equals("Todo")){
            return alumnos;
        }

        return resultado;
    }

    public List<Usuario> opcionDiscapacidad(List<Usuario> alumnos, Informes informes){
        List<Usuario> resultado = new ArrayList<>();

        if(informes.getDiscapacidad().equals("Si")){
            for (Usuario u : alumnos) {
                if (u.getInfoPersonal().getDiscapacidad().equals(true))
                    resultado.add(u);
            }
        }
        else if(informes.getDiscapacidad().equals("No")){
            for (Usuario u : alumnos) {
                if (u.getInfoPersonal().getDiscapacidad().equals(false))
                    resultado.add(u);
            }
        }
        else if(informes.getDiscapacidad().equals("Todo")){
            return alumnos;
        }

        return resultado;
    }

    public List<Usuario> opcionEstatus(List<Usuario> alumnos, Informes informes){
        List<Usuario> resultado = new ArrayList<>();

        if(informes.getEstatus().equals("Inscritos")){
            for (Usuario u : alumnos) {
                if (u.getExpediente().getEstatusEscolar().equals("Inscrito"))
                    resultado.add(u);
            }
        }
        else if(informes.getEstatus().equals("Egresados")){
            for (Usuario u : alumnos) {
                if (u.getExpediente().getEstatusEscolar().equals("Egresado"))
                    resultado.add(u);
            }
        }
        else if(informes.getEstatus().equals("Todo")){
            return alumnos;
        }

        return resultado;
    }

    public List<Usuario> opcionBecaConacyt(List<Usuario> alumnos, Informes informes){
        List<Usuario> resultado = new ArrayList<>();

        if(informes.getBecaConacyt().equals("Con Beca")){
            for (Usuario u : alumnos) {
                if (u.getExpediente().getBecaConacyt().equals(true))
                    resultado.add(u);
            }
        }
        else if(informes.getBecaConacyt().equals("Sin Beca")){
            for (Usuario u : alumnos) {
                if (u.getExpediente().getBecaConacyt().equals(false))
                    resultado.add(u);
            }
        }
        else if(informes.getBecaConacyt().equals("Todo")){
            return alumnos;
        }
        return resultado;
    }
}