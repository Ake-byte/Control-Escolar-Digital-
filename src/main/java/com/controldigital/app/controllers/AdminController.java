package com.controldigital.app.controllers;

import java.util.List;
import java.util.Map;

import com.controldigital.app.models.entity.FileStatus;
import com.controldigital.app.models.entity.Role;
import com.controldigital.app.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.controldigital.app.models.entity.Usuario;
import com.controldigital.app.service.IUsuarioService;

@Controller
@RequestMapping("/PersonalAutorizado")
public class AdminController {

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
}
