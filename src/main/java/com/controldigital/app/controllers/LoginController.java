package com.controldigital.app.controllers;

import java.security.Principal;
import java.time.Month;
import java.util.List;
import java.util.Map;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.validation.Valid;

import com.controldigital.app.models.entity.*;
import com.controldigital.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controldigital.app.util.MailSenderService;

import static com.controldigital.app.util.Fecha.*;
import static com.controldigital.app.util.Fecha.currentDate;


/**
 * Controlador que gestiona el inicio de sesión de los usuarios
 */
@Controller
public class LoginController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private InforPersonalService inforPersonalService;

    @Autowired
    private InfoAcademicaService infoAcademicaService;

    @Autowired
    private ExpedienteService expedienteService;

    @Autowired(required = true)
    private MailSenderService mailService;

    /**
     * Método que gestiona si un usuario accedió al sistema
     *
     * @param error
     * @param logout
     * @param model
     * @param principal
     * @param flash
     * @return
     */
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout, Model model, Principal principal,
                        RedirectAttributes flash) {

        if (principal != null) {
            flash.addFlashAttribute("info", "Ya ha iniciado sesión anteriormente");
            return "redirect:/";
        }

        if (error != null) {
            model.addAttribute("error", "Error al iniciar sesión. El correo o la contraseña son incorrectos.");
            //return "redirect:/login";
        }

        if (logout != null) {
            model.addAttribute("succes", "Has cerrado sesión con éxito");
            // return "redirect:login";
        }

        return "login";
    }

    /**
     * Método que devuelve el formulario para el registro de un nuevo usuario
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/register")
    public String register(Map<String, Object> model) {
        Usuario usuario = new Usuario();

        model.put("usuario", usuario);
        model.put("titulo", "Registro de Usuario");
        return "register";
    }

    /**
     * Método que guarda los datos del nuevo registro
     *
     * @param usuario
     * @param flash
     * @param result
     * @param model
     * @param status
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/register")
    public String guardar(@Valid Usuario usuario, RedirectAttributes flash, BindingResult result, Model model,
                          SessionStatus status) throws Exception {

        Usuario checarNuevoUsuario = usuarioService.findByEmail(usuario.getEmail());

        if (checarNuevoUsuario != null) {
            flash.addFlashAttribute("error", "Ya existe un usuario con esa dirección de correo electrónico.");
            return "redirect:/register";
        }

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Registro de Usuario");
            return "register";
        }

        Role role = new Role();
        InfoPersonal infoPersonal = new InfoPersonal();
        InfoAcademica infoAcademica = new InfoAcademica();
        Expediente expediente = new Expediente();

        role.setUsers(usuario);
        role.setAuthority("ROLE_USER1");
        role.setAuthorityName("Usuario Registrado");

        infoPersonal.setUsers(usuario);
        infoPersonal.setFotoStatus(FileStatus.RED1);
        infoPersonal.setActaStatus(FileStatus.RED1);
        infoPersonal.setCurpStatus(FileStatus.RED1);
        infoPersonal.setPasaporteStatus(FileStatus.RED1);

        infoAcademica.setUsers(usuario);
        infoAcademica.setCalificacionesLicenciaturaStatus(FileStatus.RED1);
        infoAcademica.setDiplomaLicenciaturaStatus(FileStatus.RED1);
        infoAcademica.setCedulaLicenciaturaStatus(FileStatus.RED1);
        infoAcademica.setAcreditacionInglesStatus(FileStatus.RED1);
        infoAcademica.setCalificacionesMaestriaStatus(FileStatus.RED1);
        infoAcademica.setCedulaMaestriaStatus(FileStatus.RED1);
        infoAcademica.setDiplomaMaestriaStatus(FileStatus.RED1);
        infoAcademica.setActaExamenMaestriaStatus(FileStatus.RED1);

        expediente.setUsers(usuario);
        Month mesActual = currentDate().getMonth();
        if(mesesA().contains(mesActual)){
            expediente.setSemestre("A" + String.valueOf(currentDate().getYear() - 2000));
        }
        else if(mesesB().contains(mesActual)){
            expediente.setSemestre("B" + String.valueOf(currentDate().getYear() - 2000));
        }

        usuario.setRoles(role);
        usuario.setInfoPersonal(infoPersonal);
        usuario.setInfoAcademica(infoAcademica);
        usuario.setExpediente(expediente);

        usuario.setEnabled(true);

        String password = usuario.getPassword();

        String bcryptPassword = passwordEncoder.encode(password);

        usuario.setPassword(bcryptPassword);

        usuarioService.save(usuario);

        roleService.saveUsuario(usuario);
        roleService.save(role);

        inforPersonalService.saveUsuario(usuario);
        inforPersonalService.save(infoPersonal);
        infoAcademicaService.saveUsuario(usuario);
        infoAcademicaService.save(infoAcademica);
        expedienteService.saveUsuario(usuario);
        expedienteService.save(expediente);



        mailService.sendEmail(usuario.getEmail(), "Bienvenido al sistema BD-LNCAE",
                "Tu permiso actual es: Usuario Registrado", usuario);


        status.setComplete();

        return "redirect:index";
    }

    /**
     * Método que regresa la vista de inicio
     *
     * @return /src/main/resources/templates/index.html
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {

        return "index";
    }


}
