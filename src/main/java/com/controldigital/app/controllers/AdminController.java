package com.controldigital.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

import com.controldigital.app.models.entity.*;
import com.controldigital.app.service.*;
import com.controldigital.app.util.Fecha;
import com.controldigital.app.util.Informes;
import com.controldigital.app.util.MailSenderService;
import com.controldigital.app.util.UserDetails;
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

import static com.controldigital.app.util.Fecha.convertDate;
import static com.controldigital.app.util.Fecha.currentDate;

/**
 * Controlador que tiene como objetivo ejecutar todas las funcionalidades del usuario de tipo "Personal Autorizado"
 */
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
    private IProductoService productoService;

    @Autowired
    private IUploadFileService uploadFileService;

    /**
     * Método que devuelve el listado de usuarios
     * @param model Parámetro que recibe la vista
     * @return /src/main/resources/templates/PersonalAutorizado/ListadoUsuarios.html
     */
    @GetMapping("/ListadoUsuarios")
    public String verUsuarios(Model model) {
        model.addAttribute("titulo", "Permisos de Usuario");
        return "PersonalAutorizado/ListadoUsuarios";
    }

    /**
     * Método que devuleve la vista para generar informes/estadísticas de los alumnos registrados en la base de datos
     * @param model
     * @return /src/main/resources/templates/PersonalAutorizado/Informes.html
     */
    @GetMapping("/Informes")
    public String informes(Model model) {

        List<Usuario> alumnos = usuarioService.findUserByRole("ROLE_USER2");
        List<UserDetails> userDetailsList = new ArrayList<>();

        for(Usuario u : alumnos){
            UserDetails userDetails = new UserDetails();
            InfoPersonal infoPersonal = inforPersonalService.findInfoPersonalByUserId(u.getId());
            InfoAcademica infoAcademica = infoAcademicaService.findInfoAcademicaByUserId(u.getId());
            Expediente expediente = expedienteService.findExpedienteByUserId(u.getId());

            userDetails.setUsuario(u);
            userDetails.setInfoPersonal(infoPersonal);
            userDetails.setInfoAcademica(infoAcademica);
            userDetails.setExpediente(expediente);

            if(infoPersonal.getFechaNacimiento() != null && expediente.getSemestre() != null) {
                userDetailsList.add(userDetails);
            }
        }

        Informes informe = new Informes();


        LocalDate date = currentDate();

        List<String> edades = new ArrayList<>();
        List<String> numeroRegistro = new ArrayList<>();

        for (UserDetails u : userDetailsList) {


                LocalDate birthday = convertDate(u.getInfoPersonal().getFechaNacimiento());

                if (!edades.contains(String.valueOf(Period.between(birthday, date).getYears()))) {
                    edades.add(String.valueOf(Period.between(birthday, date).getYears()));
                }

                if (!numeroRegistro.contains(u.getExpediente().getNumeroRegistro().substring(0,3))) {
                    numeroRegistro.add(u.getExpediente().getNumeroRegistro().substring(0,3));
                }

        }

        edades.sort(Comparator.naturalOrder());

        model.addAttribute("titulo", "Informes");
        model.addAttribute("edades", edades);
        model.addAttribute("semestres", numeroRegistro);
        model.addAttribute("informe", informe);

        return "PersonalAutorizado/Informes";
    }

    /**
     * Método que retorna como resultado el informe con los campos solicitados por Personal Autorizado
     * @param informes objeto de tipo Informe
     * @param model
     * @return /src/main/resources/templates/PersonalAutorizado/ResultadoInforme.html
     */
    @PostMapping("/Informes")
    public String generarInforme(@Valid Informes informes, Model model) {

        /*if(!informes.equals(null)){
            return "redirect:/PersonalAutorizado/Informes";
        }*/

        List<Usuario> alumnos = usuarioService.findUserByRole("ROLE_USER2");

        List<UserDetails> userDetailsList = new ArrayList<>();

        for (Usuario u : alumnos) {
            UserDetails userDetails = new UserDetails();
            InfoPersonal infoPersonal = inforPersonalService.findInfoPersonalByUserId(u.getId());
            InfoAcademica infoAcademica = infoAcademicaService.findInfoAcademicaByUserId(u.getId());
            Expediente expediente = expedienteService.findExpedienteByUserId(u.getId());

            userDetails.setUsuario(u);
            userDetails.setInfoPersonal(infoPersonal);
            userDetails.setInfoAcademica(infoAcademica);
            userDetails.setExpediente(expediente);

            if (userDetails.getInfoPersonal().getFechaNacimiento() != null
                    || userDetails.getInfoPersonal().getGenero() != null
                    || userDetails.getInfoPersonal().getPaisNacimiento() != null
                    || userDetails.getInfoPersonal().getEstadoNacimiento() != null
                    || userDetails.getInfoPersonal().getLenguaIndigena() != null
                    || userDetails.getInfoPersonal().getEnfermedadPermanente() != null
                    || userDetails.getExpediente().getGrado() != null
                    || userDetails.getExpediente().getNumeroRegistro() != null
                    || userDetails.getExpediente().getEstatusEscolar() != null
                    || userDetails.getExpediente().getBecaConacyt() != null
            ) {
                userDetailsList.add(userDetails);
            }
        }


        int numUsuarios = informesService.findAlumnosBy(informes, userDetailsList).size();

        model.addAttribute("informe", informes);
        model.addAttribute("numUsuarios", numUsuarios);
        model.addAttribute("numTotal", userDetailsList.size() - numUsuarios);

        return "PersonalAutorizado/ResultadoInforme";
    }

    @GetMapping("/verUsuariosInhabilitados")
    public String verUsuariosInhabilitados(Model model) {

        List<Role> alumnos = roleService.findUsuarioByRole("ROLE_USER2")
                .stream().filter(a -> a.getUsers().getEnabled().equals(false)).collect(Collectors.toList());

        model.addAttribute("titulo", "Alumnos");
        model.addAttribute("usuario", alumnos);
        return "PersonalAutorizado/verRol";
    }

    /**
     * Método que permite que Personal Autorizado visualice a los usuarios de tipo "Alumno"
     * @param model
     * @return /src/main/resources/templates/PersonalAutorizado/verRol.html
     */
    @GetMapping("/verAlumnos")
    public String verAlumnos(Model model) {

        List<Role> alumnos = roleService.findUsuarioByRole("ROLE_USER2")
                .stream().filter(a -> a.getUsers().getEnabled().equals(true)).collect(Collectors.toList());

        model.addAttribute("titulo", "Alumnos");
        model.addAttribute("usuario", alumnos);
        return "PersonalAutorizado/verRol";
    }

    /**
     * Método que permite que Personal Autorizado visualice a los usuarios de tipo "Usuario Registrado"
     * @param model
     * @return /src/main/resources/templates/PersonalAutorizado/verRol.html
     */
    @GetMapping("/verUsuariosRegistrados")
    public String verUsuariosRegistrados(Model model) {

        List<Role> usuariosR = roleService.findUsuarioByRole("ROLE_USER1");

        model.addAttribute("titulo", "Usuario Registrados");
        model.addAttribute("usuario", usuariosR);
        return "PersonalAutorizado/verRol";
    }

    /**
     * Método que permite que Personal Autorizado visualice a un usuario según sea su tipo de usuario en el sistema
     * @param model
     * @return Si el usuario es "Usuario Registrado", "Usuario Inhabilitado" o "Personal Autorizado":
     *              /src/main/resources/templates/PersonalAutorizado/VerUsuario.html
     *         Si el usuario es "Alumno":
     *              /src/main/resources/templates/PersonalAutorizado/VerAlumno.html
     */
    @GetMapping("/verUsuario/{id}")
    public String verAlumno(@PathVariable(value = "id") Long id, Model model) {

        Usuario usuario = null;
        if (id != null && id > 0) {
            usuario = usuarioService.findOne(id);
        } else {
            return "redirect:index";
        }

        model.addAttribute("usuario", usuario);

        if(usuario.getRoles().getAuthority().equals("ROLE_USER1") || usuario.getRoles().getAuthority().equals("ROLE_ADMIN")){
            return "PersonalAutorizado/VerUsuario";
        }
        else if(usuario.getRoles().getAuthority().equals("ROLE_USER2")){
            UserDetails userDetails = new UserDetails();
            InfoPersonal infoPersonal = inforPersonalService.findInfoPersonalByUserId(usuario.getId());
            InfoAcademica infoAcademica = infoAcademicaService.findInfoAcademicaByUserId(usuario.getId());
            Expediente expediente = expedienteService.findExpedienteByUserId(usuario.getId());

            userDetails.setUsuario(usuario);
            userDetails.setInfoPersonal(infoPersonal);
            userDetails.setInfoAcademica(infoAcademica);
            userDetails.setExpediente(expediente);

            model.addAttribute("usuario", userDetails);

            return "PersonalAutorizado/VerAlumno";
        }

        return "PersonalAutorizado/ListadoUsuarios";
    }

    /**
     * Método que muestra la vista para editar el permiso de un usuario.
     * @param id Identificador único del usuario
     * @param model
     * @return /src/main/resources/templates/PersonalAutorizado/formUsuario.html
     */
    @RequestMapping(value = "/formUsuario/{id}")
    public String editarPermisoUsuario(@PathVariable(value = "id") Long id, Map<String, Object> model) {
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

    /**
     * Método que guarda el cambio de permiso realizado en "editarPermisoUsuario"
     * @param usuario objeto del tipo Usuario
     * @param result
     * @param model
     * @param status
     * @return /src/main/resources/templates/PersonalAutorizado/ListadoUsuarios.html
     * @throws Exception
     */
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

    /**
     * Método para validar el estatus de un archivo, ya sean de carácter personal o académicos;
     * que haya subido un "Alumno" en el sistema
     * @param id
     * @param tipoArchivo
     * @param model
     * @return /src/main/resources/templates/PersonalAutorizado/opcionValidarArchivo.html
     */
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

    /**
     * Método que guarda el estatus del archivo ingresado en "opcionValidarArchivo"
     * @param request
     * @param response
     * @param userId
     * @param tipoInvalidacion
     * @param tipoArchivo
     * @param result
     * @param model
     * @param flash
     * @param status
     * @return /src/main/resources/templates/PersonalAutorizado/verUsuario.html
     */
    @PostMapping(value = "opcionValidarArchivo")
    public String guardarOpcionValidarArchivo(HttpServletRequest request, HttpServletResponse response,
                                      @ModelAttribute("userId") Long userId,
                                      @ModelAttribute("opcion") String tipoInvalidacion,
                                      @ModelAttribute("tipoArchivo") String tipoArchivo, BindingResult result,
                                      Model model, RedirectAttributes flash, SessionStatus status) {

        Usuario usuario = usuarioService.findOne(userId);
        InfoPersonal infoPersonal = inforPersonalService.findInfoPersonalByUserId(usuario.getId());
        InfoAcademica infoAcademica = infoAcademicaService.findInfoAcademicaByUserId(usuario.getId());

        String opcion = tipoInvalidacion;
        String tA = tipoArchivo;

        switch (tA) {
            case "fotoStatus":
                switch (opcion) {
                    case "1":
                        infoPersonal.setFotoStatus(FileStatus.RED2);
                        break;
                    case "2":
                        infoPersonal.setFotoStatus(FileStatus.RED3);
                        break;
                    case "3":
                        infoPersonal.setFotoStatus(FileStatus.RED4);
                        break;
                    case "4":
                        infoPersonal.setFotoStatus(FileStatus.GREEN);
                        break;
                }
                break;

            case "actaStatus":
                switch (opcion) {
                    case "1":
                        infoPersonal.setActaStatus(FileStatus.RED2);
                        break;
                    case "2":
                        infoPersonal.setActaStatus(FileStatus.RED3);
                        break;
                    case "3":
                        infoPersonal.setActaStatus(FileStatus.RED4);
                        break;
                    case "4":
                        infoPersonal.setActaStatus(FileStatus.GREEN);
                        break;
                }
                break;

            case "pasaporteStatus":
                switch (opcion) {
                    case "1":
                        infoPersonal.setPasaporteStatus(FileStatus.RED2);
                        break;
                    case "2":
                        infoPersonal.setPasaporteStatus(FileStatus.RED3);
                        break;
                    case "3":
                        infoPersonal.setPasaporteStatus(FileStatus.RED4);
                        break;
                    case "4":
                        infoPersonal.setPasaporteStatus(FileStatus.GREEN);
                        break;
                }
                break;

            case "curpStatus":
                switch (opcion) {
                    case "1":
                        infoPersonal.setCurpStatus(FileStatus.RED2);
                        break;
                    case "2":
                        infoPersonal.setCurpStatus(FileStatus.RED3);
                        break;
                    case "3":
                        infoPersonal.setCurpStatus(FileStatus.RED4);
                        break;
                    case "4":
                        infoPersonal.setCurpStatus(FileStatus.GREEN);
                        break;
                }
                break;

            case "calificacionesLicenciaturaStatus":
                switch (opcion) {
                    case "1":
                        infoAcademica.setCalificacionesLicenciaturaStatus(FileStatus.RED2);
                        break;
                    case "2":
                        infoAcademica.setCalificacionesLicenciaturaStatus(FileStatus.RED3);
                        break;
                    case "3":
                        infoAcademica.setCalificacionesLicenciaturaStatus(FileStatus.RED4);
                        break;
                    case "4":
                        infoAcademica.setCalificacionesLicenciaturaStatus(FileStatus.GREEN);
                        break;
                }
                break;

            case "diplomaLicenciaturaStatus":
                switch (opcion) {
                    case "1":
                        infoAcademica.setDiplomaLicenciaturaStatus(FileStatus.RED2);
                        break;
                    case "2":
                        infoAcademica.setDiplomaLicenciaturaStatus(FileStatus.RED3);
                        break;
                    case "3":
                        infoAcademica.setDiplomaLicenciaturaStatus(FileStatus.RED4);
                        break;
                    case "4":
                        infoAcademica.setDiplomaLicenciaturaStatus(FileStatus.GREEN);
                        break;
                }
                break;

            case "cedulaLicenciaturaStatus":
                switch (opcion) {
                    case "1":
                        infoAcademica.setCedulaLicenciaturaStatus(FileStatus.RED2);
                        break;
                    case "2":
                        infoAcademica.setCedulaLicenciaturaStatus(FileStatus.RED3);
                        break;
                    case "3":
                        infoAcademica.setCedulaLicenciaturaStatus(FileStatus.RED4);
                        break;
                    case "4":
                        infoAcademica.setCedulaLicenciaturaStatus(FileStatus.GREEN);
                        break;
                }
                break;

            case "acreditacionInglesStatus":
                switch (opcion) {
                    case "1":
                        infoAcademica.setAcreditacionInglesStatus(FileStatus.RED2);
                        break;
                    case "2":
                        infoAcademica.setAcreditacionInglesStatus(FileStatus.RED3);
                        break;
                    case "3":
                        infoAcademica.setAcreditacionInglesStatus(FileStatus.RED4);
                        break;
                    case "4":
                        infoAcademica.setAcreditacionInglesStatus(FileStatus.GREEN);
                        break;
                }
                break;

            case "calificacionesMaestriaStatus":
                switch (opcion) {
                    case "1":
                        infoAcademica.setCalificacionesMaestriaStatus(FileStatus.RED2);
                        break;
                    case "2":
                        infoAcademica.setCalificacionesMaestriaStatus(FileStatus.RED3);
                        break;
                    case "3":
                        infoAcademica.setCalificacionesMaestriaStatus(FileStatus.RED4);
                        break;
                    case "4":
                        infoAcademica.setCalificacionesMaestriaStatus(FileStatus.GREEN);
                        break;
                }
                break;

            case "actaExamenMaestriaStatus":
                switch (opcion) {
                    case "1":
                        infoAcademica.setActaExamenMaestriaStatus(FileStatus.RED2);
                        break;
                    case "2":
                        infoAcademica.setActaExamenMaestriaStatus(FileStatus.RED3);
                        break;
                    case "3":
                        infoAcademica.setActaExamenMaestriaStatus(FileStatus.RED4);
                        break;
                    case "4":
                        infoAcademica.setActaExamenMaestriaStatus(FileStatus.GREEN);
                        break;
                }
                break;

            case "diplomaMaestriaStatus":
                switch (opcion) {
                    case "1":
                        infoAcademica.setDiplomaMaestriaStatus(FileStatus.RED2);
                        break;
                    case "2":
                        infoAcademica.setDiplomaMaestriaStatus(FileStatus.RED3);
                        break;
                    case "3":
                        infoAcademica.setDiplomaMaestriaStatus(FileStatus.RED4);
                        break;
                    case "4":
                        infoAcademica.setDiplomaMaestriaStatus(FileStatus.GREEN);
                        break;
                }
                break;

            case "cedulaMaestriaStatus":
                switch (opcion) {
                    case "1":
                        infoAcademica.setCedulaMaestriaStatus(FileStatus.RED2);
                        break;
                    case "2":
                        infoAcademica.setCedulaMaestriaStatus(FileStatus.RED3);
                        break;
                    case "3":
                        infoAcademica.setCedulaMaestriaStatus(FileStatus.RED4);
                        break;
                    case "4":
                        infoAcademica.setCedulaMaestriaStatus(FileStatus.GREEN);
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

    /**
     * Método que le permite a personal autorizado crear un nuevo formato SIP
     * @param id
     * @param model
     * @return /src/main/resources/templates/PersonalAutorizado/formSIP.html
     */
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

    @RequestMapping(value = "/formSIP2/{idSIP}/{idUsuario}")
    public String editarSip(@PathVariable(value = "idUsuario") Long idUsuario,
                            @PathVariable(value = "idSIP") Long idSip ,Map<String, Object> model) {
        Usuario usuario = null;
        SIP sip = null;
        if (idUsuario > 0 && idSip > 0) {
            usuario = usuarioService.findOne(idUsuario);
            sip = sipService.findOne(idSip);
            ((Model) model).addAttribute("titulo", "Agregar SIP");
            ((Model) model).addAttribute("sip", sip);
            return "PersonalAutorizado/formSIP2";
        } else {
            return "redirect:/PersonalAutorizado/ListadoUsuarios";
        }
    }

    @PostMapping(value = "/formSIP2")
    public String guardarSip2(@Valid SIP sip, BindingResult result, Model model,
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

        return "redirect:/PersonalAutorizado/verUsuario/" + usuario.getId();

    }

    /**
     * Método que guarda el formato SIP creado por el Personal Autorizado
     * @param sip
     * @param result
     * @param model
     * @param file
     * @return /src/main/resources/templates/PersonalAutorizado/verUsuario.html
     */
    @PostMapping(value = "/formSIP")
    public String guardarSip(@Valid SIP sip, BindingResult result, Model model,
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

        return "redirect:/PersonalAutorizado/verUsuario/" + usuario.getId();

    }

    /**
     * Método que le permite al Personal Autorizado descargar el formato SIP de un "Alumno"
     * @param id identificador único del formato SIP
     * @param request
     * @return
     */
    @GetMapping(value = "/descargarSip/{id}")
    public ResponseEntity<Resource> descargarArchivo(@PathVariable Long id, HttpServletRequest request) {

        SIP sip = sipService.findOne(id);

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

    /**
     * Método que elimina a un usuario del sistema definitivamente de la base de datos. Es decir, no quedan
     * registros alguno de ese usuario dentro del sistema
     * @param id
     * @return /src/main/resources/templates/PersonalAutorizado/ListadoUsuarios.html
     */
    @GetMapping(value = "/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable(value = "id") Long id) {

        if (id > 0) {
            Usuario usuario = usuarioService.findOne(id);
            //InfoPersonal infoPersonal = inforPersonalService.findOne(usuario.getId());
            //InfoAcademica infoAcademica = infoAcademicaService.findOne(usuario.getId());
            //Expediente expediente = expedienteService.findExpedienteByUserId(usuario.getId());

            //infoAcademicaService.delete(infoAcademica.getId());
            //inforPersonalService.delete(infoPersonal.getId());

            //expedienteService.delete(expediente.getId());
            usuarioService.delete(id);
        }

        return "redirect:/PersonalAutorizado/ListadoUsuarios";
    }

    @GetMapping(value = "/descargarArchivoProducto/{id}")
    public ResponseEntity<Resource> descargarArchivoProducto(@PathVariable Long id, HttpServletRequest request) {

        Producto producto = productoService.findOne(id);

        String filename = producto.getArchivoProducto();

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

    @RequestMapping(value = "/eliminarSIP/{id}")
    public String eliminarSIP(@PathVariable(value = "id") Long id) {

        SIP sip = sipService.findOne(id);
        Usuario usuario = usuarioService.findOne(sip.getUsers().getId());

        if (id > 0) {
            sipService.delete(id);
        }

        return "redirect:/PersonalAutorizado/verUsuario/" + usuario.getId();
    }

    @GetMapping(value = "/descargarArchivoInfoPersonal/{tipoArchivo}/{id}")
    public ResponseEntity<Resource> descargarArchivoInfoPersonal(@PathVariable String tipoArchivo, @PathVariable Long id, HttpServletRequest request) {

        InfoPersonal infoPersonal = inforPersonalService.findOne(id);

        String filename = null;

        if(tipoArchivo.equals("Foto")){
            filename = infoPersonal.getFotoActual();
        }
        else if(tipoArchivo.equals("ActaNacimiento")){
            filename = infoPersonal.getActaNacimiento();
        }
        else if(tipoArchivo.equals("Pasaporte")){
            filename = infoPersonal.getPasaporte();
        }
        else if(tipoArchivo.equals("Curp")){
            filename = infoPersonal.getCedulaCURP();
        }

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

    @GetMapping(value = "/descargarArchivoInfoAcademica/{tipoArchivo}/{id}")
    public ResponseEntity<Resource> descargarArchivoInfoAcademica(@PathVariable String tipoArchivo, @PathVariable Long id, HttpServletRequest request) {

        InfoAcademica infoAcademica = infoAcademicaService.findOne(id);

        String filename = null;

        if(tipoArchivo.equals("calificacionesLicenciatura")){
            filename = infoAcademica.getCalificacionesLicenciatura();
        }
        else if(tipoArchivo.equals("diplomaLicenciatura")){
            filename = infoAcademica.getDiplomaLicenciatura();
        }
        else if(tipoArchivo.equals("cedulaLicenciatura")){
            filename = infoAcademica.getCedulaLicenciatura();
        }
        else if(tipoArchivo.equals("acreditacionIngles")){
            filename = infoAcademica.getAcreditacionIngles();
        }
        else if(tipoArchivo.equals("calificacionesMaestria")){
            filename = infoAcademica.getCalificacionesMaestria();
        }
        else if(tipoArchivo.equals("actaExamenMaestria")){
            filename = infoAcademica.getActaExamenMaestria();
        }
        else if(tipoArchivo.equals("diplomaMaestria")){
            filename = infoAcademica.getDiplomaMaestria();
        }
        else if(tipoArchivo.equals("cedulaMaestria")){
            filename = infoAcademica.getCedulaMaestria();
        }

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
}
