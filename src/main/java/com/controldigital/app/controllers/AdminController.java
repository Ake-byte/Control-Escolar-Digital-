package com.controldigital.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.controldigital.app.models.entity.FileStatus;
import com.controldigital.app.models.entity.Role;
import com.controldigital.app.service.IRoleService;
import com.controldigital.app.util.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.controldigital.app.models.entity.Usuario;
import com.controldigital.app.service.IUsuarioService;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@RequestMapping("/PersonalAutorizado")
public class AdminController {

    @Autowired(required = true)
    private MailSenderService mailService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IRoleService roleService;

    @GetMapping("/ListadoUsuarios")
    public String verUsuarios(Model model) {
        model.addAttribute("titulo", "Permisos de Usuario");
        return "PersonalAutorizado/ListadoUsuarios";
    }

    @GetMapping("/TramitesSIP")
    public String tramitesSIP(Model model) {
        model.addAttribute("titulo", "Trámites SIP");
        return "PersonalAutorizado/TramitesSIP";
    }

    @GetMapping("/TramitesInternos")
    public String tramitesInternos(Model model) {
        model.addAttribute("titulo", "Trámites Internos");
        return "PersonalAutorizado/TramitesInternos";
    }

    @GetMapping("/Expedientes")
    public String expedientes(Model model) {
        model.addAttribute("titulo", "Expedientes");
        return "PersonalAutorizado/Expedientes";
    }

    @GetMapping("/ActasYNombramientos")
    public String actasYNombramientos(Model model) {
        model.addAttribute("titulo", "Actas y Nombramientos");
        return "PersonalAutorizado/ActasYNombramientos";
    }


    @GetMapping("/Informes")
    public String informes(Model model) {
        model.addAttribute("titulo", "Informes");
        return "PersonalAutorizado/Informes";
    }

    @GetMapping("/verAlumnos")
    public String verAlumnos(Model model) {

        List<Role> alumnos = roleService.findUsuarioByRole("ROLE_USER2");


        model.addAttribute("titulo", "Alumnos");
        model.addAttribute("usuario", alumnos);
        return "PersonalAutorizado/verRol";
    }

    @GetMapping("/verUsuariosRegistrados")
    public String verUsuariosRegistrados(Model model) {

        List<Role> usuariosR = roleService.findUsuarioByRole("ROLE_USER1");


        model.addAttribute("titulo", "Usuario Registrados");
        model.addAttribute("usuario", usuariosR);
        return "PersonalAutorizado/verRol";
    }

    @GetMapping("/verUsuario/{id}")
    public String verAlumno(@PathVariable(value = "id") Long id, Map<String, Object> model) {

        Usuario usuario = null;
        if (id != null && id > 0) {
            usuario = usuarioService.findOne(id);
        } else {
            return "redirect:index";
        }

        model.put("usuario", usuario);

        return "PersonalAutorizado/VerUsuario";
    }

    @RequestMapping(value = "/formUsuario/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
        Usuario usuario = null;

        if (id > 0) {
            usuario = usuarioService.findOne(id);
            model.put("usuario", usuario);
        } else {
            return "redirect:/PersonalAutorizado/ListadoUsuarios";
        }

        List<String> permisosUsuario = new ArrayList<>();
        permisosUsuario.add("Usuario Registrado");
        permisosUsuario.add("Alumno");
        permisosUsuario.add("Personal Autorizado");
        permisosUsuario.add("Usuario Inhabilitado");

        model.put("permisos", permisosUsuario);
        model.put("titulo", "Editar Rol de Usuario");

        return "PersonalAutorizado/formUsuario";

    }

    @PostMapping(value = "formUsuario")
    public String guardar(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status)
            throws Exception {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Editar Rol de Usuario");
            return "formUsuario";
        }

        Role roles = usuario.getRoles();

        Role roleUsuario = roleService.findRoleByUserId(usuario.getId());

        switch (usuario.getRoles().getAuthorityName()) {

            case "Usuario Registrado":
                roles.setAuthorityName("Usuario Registrado");
                roles.setAuthority("ROLE_USER1");
                break;

            case "Alumno":
                roles.setAuthorityName("Alumno");
                roles.setAuthority("ROLE_USER2");
                List<Usuario> usuarios = usuarioService.findall();
                break;

            case "Personal Autorizado":
                roles.setAuthorityName("Personal Autorizado");
                roles.setAuthority("ROLE_ADMIN");
                break;

            case "Usuario Inhabilitado":
                roles.setAuthorityName("Usuario Inhabilitado");
                roles.setAuthority("ROLE_USER4");
                usuario.setEnabled(false);
                break;

            default:
                break;
        }

        mailService.sendEmail(usuario.getEmail(), "Cambios de permisos en el sistema BD-LNCAE",
                "Tu permiso actual es: " + usuario.getRoles().getAuthorityName(), usuario);
        usuarioService.save(usuario);
        status.setComplete();

        return "redirect:/PersonalAutorizado/ListadoUsuarios";
    }


    @GetMapping("/validarArchivo/{id}/{tipoArchivo}")
    public String validarArchivo(@PathVariable(value = "id") Long id,
                                 @PathVariable(value = "tipoArchivo") String tipoArchivo, Map<String, Object> model) {

        Usuario usuario = null;
        if (id != null && id > 0) {
            usuario = usuarioService.findOne(id);
        } else {
            return "redirect:index";
        }

        switch (tipoArchivo) {
            case "fotoStatus":
                usuario.setFotoStatus(FileStatus.GREEN);
                break;

            case "actaStatus":
                usuario.setActaStatus(FileStatus.GREEN);
                break;

            case "pasaporteStatus":
                usuario.setPasaporteStatus(FileStatus.GREEN);
                break;

            case "curpStatus":
                usuario.setCurpStatus(FileStatus.GREEN);
                break;

            default:
                break;
        }

        usuarioService.save(usuario);
        return "redirect:/PersonalAutorizado/verUsuario/" + usuario.getId();
    }

    @GetMapping("/invalidarArchivo/{id}/{tipoArchivo}")
    public String invalidarArchivo(@PathVariable(value = "id") Long id,
                                 @PathVariable(value = "tipoArchivo") String tipoArchivo, Map<String, Object> model) {

        Usuario usuario = null;
        if (id != null && id > 0) {
            usuario = usuarioService.findOne(id);
        } else {
            return "redirect:index";
        }

        switch (tipoArchivo) {
            case "fotoStatus":
                usuario.setFotoStatus(FileStatus.RED);
                break;

            case "actaStatus":
                usuario.setActaStatus(FileStatus.RED);
                break;

            case "pasaporteStatus":
                usuario.setPasaporteStatus(FileStatus.RED);
                break;

            case "curpStatus":
                usuario.setCurpStatus(FileStatus.RED);
                break;

            default:
                break;
        }

        usuarioService.save(usuario);
        return "redirect:/PersonalAutorizado/verUsuario/" + usuario.getId();
    }

    @GetMapping("/Graficar")
    public String generarInforme(@RequestParam(name = "genero") String genero){

        if(!genero.isEmpty()){
            List<Usuario> usuariosByGenero = usuarioService.findall().stream().filter(u -> u.getGenero().equals(genero)).collect(Collectors.toList());
        }

        List<Usuario> nacionalidades = usuarioService.findall();
        List<Usuario> edades = usuarioService.findall();
        List<Usuario> lugarNacimiento = usuarioService.findall();



        return null;
    }

    @GetMapping("/barChart")
    public String getAllEmployee(Model model) {

        //List<String> nameList= usuarioService.findall().stream().map(x->x.getNombre()).collect(Collectors.toList());
        //List<Integer> ageList = usuarioService.findall().stream().map(x-> x.getEdad()).collect(Collectors.toList());

        List<String> genero = new ArrayList<>();
        genero.add("Hombre");
        genero.add("Mujer");

        Integer numUsuarios = usuarioService.findall().size();

        model.addAttribute("name", genero);
        model.addAttribute("age", numUsuarios);

        return "PersonalAutorizado/BarChart";

    }

    @GetMapping("/pieChart")
    public String pieChart(Model model) {
        List<Usuario> usuarios = usuarioService.findall();
        List<Role> alumnos = roleService.findUsuarioByRole("ROLE_USER2");

        int hombres = 0;
        int mujeres = 0;

        for(Role u: alumnos){
            if(u.getUsers().getGenero().equals("Hombre")){
                hombres++;
            }
            else{
                mujeres++;
            }
        }

        model.addAttribute("hombres", hombres);
        model.addAttribute("mujeres", mujeres);
        return "PersonalAutorizado/pieChart";

    }
}
