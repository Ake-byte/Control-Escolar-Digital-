package com.controldigital.app.controllers;

import com.controldigital.app.models.entity.Usuario;
import com.controldigital.app.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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

}
