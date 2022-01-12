package com.controldigital.app.controllers;

import com.controldigital.app.models.entity.Usuario;
import com.controldigital.app.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Sistema")
public class SistemaController {
    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping(value = "/InformacionSistema")
    public String verDatosSistema(Model model, Authentication authentication, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Usuario usuario = usuarioService.findByEmail(auth.getName());

        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "Datos Sistema");
        return "Sistema/InformacionSistema";
    }

    @GetMapping(value = "/EditarInformacionSistema")
    public String editarDatos(Map<String, Object> model, Authentication authentication, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Usuario usuario = usuarioService.findByEmail(auth.getName());


        ((Model) model).addAttribute("usuario", usuario);
        ((Model) model).addAttribute("titulo", "Editar Información Sistema");
        return "Sistema/EditarInformacionSistema";
    }

    @PostMapping(value = "/EditarInformacionSistema")
    public String guardarDatos(@Valid Usuario usuario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Editar Datos");
            return "Personal/EditarInformacionSistema";
        }

        usuarioService.save(usuario);

        return "redirect:InformacionSistema";
    }
}