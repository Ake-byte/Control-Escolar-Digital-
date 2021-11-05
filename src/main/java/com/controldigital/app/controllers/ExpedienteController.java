package com.controldigital.app.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.controldigital.app.models.entity.Usuario;
import com.controldigital.app.service.IUsuarioService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Expediente")
public class ExpedienteController {

	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping(value = "/MiExpediente")
	public String verPerfil(Model model, Authentication authentication, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Usuario usuario = usuarioService.findByEmail(auth.getName());
		
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "Expediente");
		return "Expediente/MiExpediente";
	}

	@GetMapping(value = "/EditarExpediente")
	public String editarExpediente(Map<String, Object> model, Authentication authentication, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioService.findByEmail(auth.getName());



		return "Personal/EditarInformacionPersonal";
	}
}
