package com.controldigital.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

import com.controldigital.app.models.entity.*;
import com.controldigital.app.service.*;
import com.controldigital.app.util.Informes;
import com.controldigital.app.util.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
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

    @Autowired
    private ISipService sipService;

    @Autowired
    private IUploadFileService uploadFileService;

    @GetMapping("/ListadoUsuarios")
    public String verUsuarios(Model model) {
        model.addAttribute("titulo", "Permisos de Usuario");
        return "PersonalAutorizado/ListadoUsuarios";
    }

    @GetMapping("/Informes")
    public String informes(Model model) {

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
        if (id != null && id > 0) {
            usuario = usuarioService.findOne(id);
        } else {
            return "redirect:index";
        }

        model.addAttribute("usuario", usuario);

        if(usuario.getRoles().getAuthority().equals("ROLE_USER1")){
            return "PersonalAutorizado/VerUsuarioRegistrado";
        }
        else if(usuario.getRoles().getAuthority().equals("ROLE_USER2") || usuario.getRoles().getAuthority().equals("ROLE_USER4")){
            return "PersonalAutorizado/VerUsuario";
        }

        return "PersonalAutorizado/ListadoUsuarios";
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
                if(usuario.getEnabled().equals(false))
                    usuario.setEnabled(true);
                break;

            case "Alumno":
                roles.setAuthorityName("Alumno");
                roles.setAuthority("ROLE_USER2");
                if(usuario.getEnabled().equals(false))
                    usuario.setEnabled(true);
                break;

            case "Personal Autorizado":
                roles.setAuthorityName("Personal Autorizado");
                roles.setAuthority("ROLE_ADMIN");
                if(usuario.getEnabled().equals(false))
                    usuario.setEnabled(true);
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


    @GetMapping("/opcionValidarArchivo/{id}/{tipoArchivo}")
    public String opcionValidarArchivo(@PathVariable(value = "id") Long id,
                                   @PathVariable(value = "tipoArchivo") String tipoArchivo, Model model) {

        Usuario usuario = null;
        if (id != null && id > 0) {
            usuario = usuarioService.findOne(id);
        } else {
            return "redirect:index";
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("tipoArchivo", tipoArchivo);
        return "PersonalAutorizado/opcionValidarArchivo";
    }

    @PostMapping(value = "opcionValidarArchivo")
    public String guardarOpcionValidarArchivo(HttpServletRequest request, HttpServletResponse response,
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
                        usuario.getInfoPersonal().setFotoStatus(FileStatus.RED2);
                        break;
                    case "2":
                        usuario.getInfoPersonal().setFotoStatus(FileStatus.RED3);
                        break;
                    case "3":
                        usuario.getInfoPersonal().setFotoStatus(FileStatus.RED4);
                        break;
                    case "4":
                        usuario.getInfoPersonal().setFotoStatus(FileStatus.GREEN);
                        break;
                }
                break;

            case "actaStatus":
                switch (opcion) {
                    case "1":
                        usuario.getInfoPersonal().setActaStatus(FileStatus.RED2);
                        break;
                    case "2":
                        usuario.getInfoPersonal().setActaStatus(FileStatus.RED3);
                        break;
                    case "3":
                        usuario.getInfoPersonal().setActaStatus(FileStatus.RED4);
                        break;
                    case "4":
                        usuario.getInfoPersonal().setActaStatus(FileStatus.GREEN);
                        break;
                }
                break;

            case "pasaporteStatus":
                switch (opcion) {
                    case "1":
                        usuario.getInfoPersonal().setPasaporteStatus(FileStatus.RED2);
                        break;
                    case "2":
                        usuario.getInfoPersonal().setPasaporteStatus(FileStatus.RED3);
                        break;
                    case "3":
                        usuario.getInfoPersonal().setPasaporteStatus(FileStatus.RED4);
                        break;
                    case "4":
                        usuario.getInfoPersonal().setPasaporteStatus(FileStatus.GREEN);
                        break;
                }
                break;

            case "curpStatus":
                switch (opcion) {
                    case "1":
                        usuario.getInfoPersonal().setCurpStatus(FileStatus.RED2);
                        break;
                    case "2":
                        usuario.getInfoPersonal().setCurpStatus(FileStatus.RED3);
                        break;
                    case "3":
                        usuario.getInfoPersonal().setCurpStatus(FileStatus.RED4);
                        break;
                    case "4":
                        usuario.getInfoPersonal().setCurpStatus(FileStatus.GREEN);
                        break;
                }
                break;

            case "calificacionesLicenciaturaStatus":
                switch (opcion) {
                    case "1":
                        usuario.getInfoAcademica().setCalificacionesLicenciaturaStatus(FileStatus.RED2);
                        break;
                    case "2":
                        usuario.getInfoAcademica().setCalificacionesLicenciaturaStatus(FileStatus.RED3);
                        break;
                    case "3":
                        usuario.getInfoAcademica().setCalificacionesLicenciaturaStatus(FileStatus.RED4);
                        break;
                    case "4":
                        usuario.getInfoAcademica().setCalificacionesLicenciaturaStatus(FileStatus.GREEN);
                        break;
                }
                break;

            case "diplomaLicenciaturaStatus":
                switch (opcion) {
                    case "1":
                        usuario.getInfoAcademica().setDiplomaLicenciaturaStatus(FileStatus.RED2);
                        break;
                    case "2":
                        usuario.getInfoAcademica().setDiplomaLicenciaturaStatus(FileStatus.RED3);
                        break;
                    case "3":
                        usuario.getInfoAcademica().setDiplomaLicenciaturaStatus(FileStatus.RED4);
                        break;
                    case "4":
                        usuario.getInfoAcademica().setDiplomaLicenciaturaStatus(FileStatus.GREEN);
                        break;
                }
                break;

            case "cedulaLicenciaturaStatus":
                switch (opcion) {
                    case "1":
                        usuario.getInfoAcademica().setCedulaLicenciaturaStatus(FileStatus.RED2);
                        break;
                    case "2":
                        usuario.getInfoAcademica().setCedulaLicenciaturaStatus(FileStatus.RED3);
                        break;
                    case "3":
                        usuario.getInfoAcademica().setCedulaLicenciaturaStatus(FileStatus.RED4);
                        break;
                    case "4":
                        usuario.getInfoAcademica().setCedulaLicenciaturaStatus(FileStatus.GREEN);
                        break;
                }
                break;

            case "acreditacionInglesStatus":
                switch (opcion) {
                    case "1":
                        usuario.getInfoAcademica().setAcreditacionInglesStatus(FileStatus.RED2);
                        break;
                    case "2":
                        usuario.getInfoAcademica().setAcreditacionInglesStatus(FileStatus.RED3);
                        break;
                    case "3":
                        usuario.getInfoAcademica().setAcreditacionInglesStatus(FileStatus.RED4);
                        break;
                    case "4":
                        usuario.getInfoAcademica().setAcreditacionInglesStatus(FileStatus.GREEN);
                        break;
                }
                break;

            case "calificacionesMaestriaStatus":
                switch (opcion) {
                    case "1":
                        usuario.getInfoAcademica().setCalificacionesMaestriaStatus(FileStatus.RED2);
                        break;
                    case "2":
                        usuario.getInfoAcademica().setCalificacionesMaestriaStatus(FileStatus.RED3);
                        break;
                    case "3":
                        usuario.getInfoAcademica().setCalificacionesMaestriaStatus(FileStatus.RED4);
                        break;
                    case "4":
                        usuario.getInfoAcademica().setCalificacionesMaestriaStatus(FileStatus.GREEN);
                        break;
                }
                break;

            case "actaExamenMaestriaStatus":
                switch (opcion) {
                    case "1":
                        usuario.getInfoAcademica().setActaExamenMaestriaStatus(FileStatus.RED2);
                        break;
                    case "2":
                        usuario.getInfoAcademica().setActaExamenMaestriaStatus(FileStatus.RED3);
                        break;
                    case "3":
                        usuario.getInfoAcademica().setActaExamenMaestriaStatus(FileStatus.RED4);
                        break;
                    case "4":
                        usuario.getInfoAcademica().setActaExamenMaestriaStatus(FileStatus.GREEN);
                        break;
                }
                break;

            case "diplomaMaestriaStatus":
                switch (opcion) {
                    case "1":
                        usuario.getInfoAcademica().setDiplomaMaestriaStatus(FileStatus.RED2);
                        break;
                    case "2":
                        usuario.getInfoAcademica().setDiplomaMaestriaStatus(FileStatus.RED3);
                        break;
                    case "3":
                        usuario.getInfoAcademica().setDiplomaMaestriaStatus(FileStatus.RED4);
                        break;
                    case "4":
                        usuario.getInfoAcademica().setDiplomaMaestriaStatus(FileStatus.GREEN);
                        break;
                }
                break;

            case "cedulaMaestriaStatus":
                switch (opcion) {
                    case "1":
                        usuario.getInfoAcademica().setCedulaMaestriaStatus(FileStatus.RED2);
                        break;
                    case "2":
                        usuario.getInfoAcademica().setCedulaMaestriaStatus(FileStatus.RED3);
                        break;
                    case "3":
                        usuario.getInfoAcademica().setCedulaMaestriaStatus(FileStatus.RED4);
                        break;
                    case "4":
                        usuario.getInfoAcademica().setCedulaMaestriaStatus(FileStatus.GREEN);
                        break;
                }
                break;

            default:
                break;
        }
        //flash.addFlashAttribute("success", tA + " " + opcion);
        usuarioService.save(usuario);
        return "redirect:/PersonalAutorizado/verUsuario/" + usuario.getId();
    }

    @RequestMapping(value = "/formSIP/{id}")
    public String agregarSip(@PathVariable(value = "id") Long id, Map<String, Object> model) {
        Usuario usuario = null;

        if (id > 0) {
            usuario = usuarioService.findOne(id);
            //model.put("usuario", usuario);
        } else {
            return "redirect:/PersonalAutorizado/ListadoUsuarios";
        }

        SIP sip = new SIP();
        sip.setUsers(usuario);

        model.put("titulo", "Agregar SIP");
        model.put("sip", sip);
        //model.put("idUsuario", usuario.getId());
        return "PersonalAutorizado/formSIP";

    }

    @PostMapping(value = "/formSIP")
    public String guardarrSip(@Valid SIP sip, BindingResult result, Model model,
                              @RequestParam("file") MultipartFile file) {
        Usuario usuario = sip.getUsers();

        if(!file.isEmpty()){
            if(sip.getArchivoSip() != null && sip.getArchivoSip().length() > 0){
                uploadFileService.delete(sip.getArchivoSip());
            }
            String uniqueFilename = null;
            try {
                uniqueFilename = uploadFileService.copy(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            sip.setArchivoSip(uniqueFilename);
        } else {
            if(sip.getArchivoSip() == null){
                sip.setArchivoSip("");
            }
        }

        sipService.saveSip(sip);
        sipService.saveUsuario(usuario);

        return "PersonalAutorizado/ListadoUsuarios";

    }

    @GetMapping(value = "/descargarSip/{id}")
    public ResponseEntity<Resource> descargarArchivo(@PathVariable Long id, HttpServletRequest request) {

        SIP sip = sipService.findSipById(id);

        String filename = sip.getArchivoSip();

        Resource recurso = null;
        try {
            recurso = uploadFileService.load(filename);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(recurso.getFile().getAbsolutePath());
        } catch (IOException ex) {
            // logger.info("Could not determine file type.");
        }
        // Fallback to the default content type if type could not be determined
        if (contentType == null) {

            contentType = "application/octet-stream";

        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
                .body(recurso);

    }

    @RequestMapping(value = "/eliminarUsuario/{id}")
    public String eliminar(@PathVariable(value = "id") Long id) {

        if (id > 0) {
            usuarioService.delete(id);
        }

        return "redirect:/PersonalAutorizado/ListadoUsuarios";
    }

}
