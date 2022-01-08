package com.controldigital.app.controllers;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

import com.controldigital.app.models.entity.*;
import com.controldigital.app.service.*;
import com.controldigital.app.util.Informes;
import com.controldigital.app.util.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private IInformesService informesService;

    @Autowired
    private InforPersonalService inforPersonalService;

    @Autowired
    private InfoAcademicaService infoAcademicaService;

    @Autowired
    private ExpedienteService expedienteService;

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

        //List<Role> alumnos = roleService.findUsuarioByRole("ROLE_USER2");

        List<Usuario> alumnos = usuarioService.findUserByRole("ROLE_USER2");

        Informes informe = new Informes();


        Date input = new Date();
        Instant instant = input.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate date = zdt.toLocalDate();

        int year = date.getYear();
        List<String> edades = new ArrayList<>();
        List<String> semestre = new ArrayList<>();

        for (Usuario u : alumnos) {
            LocalDate birthday = convertDate(u.getInfoPersonal().getFechaNacimiento());

            if (!edades.contains(String.valueOf(Period.between(birthday, date).getYears()))) {
                edades.add(String.valueOf(Period.between(birthday, date).getYears()));
            }

            if (!semestre.contains(u.getExpediente().getSemestre())) {
                semestre.add(u.getExpediente().getSemestre());
            }
        }

        edades.sort(Comparator.naturalOrder());

        model.addAttribute("titulo", "Informes");
        model.addAttribute("edades", edades);
        model.addAttribute("semestres", semestre);
        model.addAttribute("informe", informe);

        return "PersonalAutorizado/Informes";
    }

    public static LocalDate convertDate(Date date) {
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    @PostMapping("/Informes")
    public String generarInforme(@Valid Informes informes, Model model) {

        /*if(!informes.equals(null)){
            return "redirect:/PersonalAutorizado/Informes";
        }*/

        List<Usuario> alumnos = usuarioService.findUserByRole("ROLE_USER2");

        int numUsuarios = informesService.findAlumnosBy(informes).size();

        model.addAttribute("informe", informes);
        model.addAttribute("numUsuarios", numUsuarios);
        model.addAttribute("numTotal", alumnos.size() - numUsuarios);

        return "PersonalAutorizado/ResultadoInforme";
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
    public String verAlumno(@PathVariable(value = "id") Long id, Model model) {

        Usuario usuario = null;
        InfoPersonal usuarioInfoPersonal = null;
        InfoAcademica usuarioInfoAcademica = null;
        Expediente expediente = null;

        UserDetails usuarioDetails = new UserDetails();
        if (id != null && id > 0) {
            usuario = usuarioService.findOne(id);
            usuarioInfoPersonal = inforPersonalService.findInfoPersonalByUserId(usuario.getId());
            usuarioInfoAcademica = infoAcademicaService.findInfoAcademicaByUserId(usuario.getId());
            expediente = expedienteService.findExpedienteByUserId(usuario.getId());
            usuarioDetails.setUsuario(usuario);
            usuarioDetails.setInfoPersonal(usuarioInfoPersonal);
            usuarioDetails.setInfoAcademica(usuarioInfoAcademica);
            usuarioDetails.setExpediente(expediente);
        } else {
            return "redirect:index";
        }




        model.addAttribute("usuario", usuarioDetails);
        //model.addAttribute("usuarioInfoPersonal", usuarioInfoPersonal);
        //model.addAttribute("usuarioInfoAcademica", usuarioInfoAcademica);

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


    /*@GetMapping("/validarArchivo/{id}/{tipoArchivo}")
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
                usuario.getInfoPersonal().setFotoStatus(FileStatus.GREEN);
                usuario.getInfoPersonal().setFotoInvalidStatus(InvalidStatus.VALID);
                break;

            case "actaStatus":
                usuario.getInfoPersonal().setActaStatus(FileStatus.GREEN);
                usuario.getInfoPersonal().setActaInvalidStatus(InvalidStatus.VALID);
                break;

            case "pasaporteStatus":
                usuario.getInfoPersonal().setPasaporteStatus(FileStatus.GREEN);
                usuario.getInfoPersonal().setPasaporteInvalidStatus(InvalidStatus.VALID);
                break;

            case "curpStatus":
                usuario.getInfoPersonal().setCurpStatus(FileStatus.GREEN);
                usuario.getInfoPersonal().setCurpInvalidStatus(InvalidStatus.VALID);
                break;

            default:
                break;
        }

        usuarioService.save(usuario);
        return "redirect:/PersonalAutorizado/verUsuario/" + usuario.getId();
    }
*/
    @GetMapping("/invalidarArchivo/{id}/{tipoArchivo}")
    public String invalidarArchivo(@PathVariable(value = "id") Long id,
                                   @PathVariable(value = "tipoArchivo") String tipoArchivo, Model model) {

        Usuario usuario = null;
        if (id != null && id > 0) {
            usuario = usuarioService.findOne(id);
        } else {
            return "redirect:index";
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("tipoArchivo", tipoArchivo);
        return "PersonalAutorizado/OpcionesInvalidarArchivo";
    }

    /*@PostMapping(value = "invalidarArchivo")
    public String guardarInvalidacion(HttpServletRequest request, HttpServletResponse response,
                                      @ModelAttribute("userId") Long userId,
                                      @ModelAttribute("opcion") String tipoInvalidacion,
                                      @ModelAttribute("tipoArchivo") String tipoArchivo, BindingResult result,
                                      Model model, RedirectAttributes flash, SessionStatus status) {

        Usuario usuario = usuarioService.findOne(userId);

        String opcion = tipoInvalidacion;
        String tA = tipoArchivo;

        switch (tA) {
            case "fotoStatus":
                switch (opcion) {
                    case "1":
                        usuario.getInfoPersonal().setFotoInvalidStatus(InvalidStatus.DOC);
                        break;
                    case "2":
                        usuario.getInfoPersonal().setFotoInvalidStatus(InvalidStatus.LEG);
                        break;
                    case "3":
                        usuario.getInfoPersonal().setFotoInvalidStatus(InvalidStatus.APO);
                        break;
                }
                usuario.getInfoPersonal().setFotoStatus(FileStatus.RED);
                break;

            case "actaStatus":
                switch (opcion) {
                    case "1":
                        usuario.getInfoPersonal().setActaInvalidStatus(InvalidStatus.DOC);
                        break;
                    case "2":
                        usuario.getInfoPersonal().setActaInvalidStatus(InvalidStatus.LEG);
                        break;
                    case "3":
                        usuario.getInfoPersonal().setActaInvalidStatus(InvalidStatus.APO);
                        break;
                }
                usuario.getInfoPersonal().setActaStatus(FileStatus.RED);
                break;

            case "pasaporteStatus":
                switch (opcion) {
                    case "1":
                        usuario.getInfoPersonal().setPasaporteInvalidStatus(InvalidStatus.DOC);
                        break;
                    case "2":
                        usuario.getInfoPersonal().setPasaporteInvalidStatus(InvalidStatus.LEG);
                        break;
                    case "3":
                        usuario.getInfoPersonal().setPasaporteInvalidStatus(InvalidStatus.APO);
                        break;
                }
                usuario.getInfoPersonal().setPasaporteStatus(FileStatus.RED);
                break;

            case "curpStatus":
                switch (opcion) {
                    case "1":
                        usuario.getInfoPersonal().setCurpInvalidStatus(InvalidStatus.DOC);
                        break;
                    case "2":
                        usuario.getInfoPersonal().setCurpInvalidStatus(InvalidStatus.LEG);
                        break;
                    case "3":
                        usuario.getInfoPersonal().setCurpInvalidStatus(InvalidStatus.APO);
                        break;
                }
                usuario.getInfoPersonal().setCurpStatus(FileStatus.RED);
                break;

            default:
                break;
        }
        flash.addFlashAttribute("success", tA + " " + opcion);
        usuarioService.save(usuario);
        return "redirect:/PersonalAutorizado/verUsuario/" + usuario.getId();
    }
*/

}
