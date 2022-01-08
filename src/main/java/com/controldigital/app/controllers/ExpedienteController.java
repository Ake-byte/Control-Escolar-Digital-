package com.controldigital.app.controllers;

import javax.servlet.http.HttpServletRequest;

import com.controldigital.app.models.entity.Expediente;
import com.controldigital.app.service.IExpedienteService;
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

	@Autowired
	private IExpedienteService expedienteService;

	@GetMapping(value = "/MiExpediente")
	public String verPerfil(Model model, Authentication authentication, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Usuario usuario = usuarioService.findByEmail(auth.getName());

		Expediente expediente = expedienteService.findExpedienteByUserId(usuario.getId());
		
		model.addAttribute("usuario", expediente);
		model.addAttribute("titulo", "Expediente");
		return "Expediente/MiExpediente";
	}

	@GetMapping(value = "/EditarExpediente")
	public String editarExpediente(Map<String, Object> model, Authentication authentication, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioService.findByEmail(auth.getName());


		((Model) model).addAttribute("usuario", usuario);
		((Model) model).addAttribute("titulo", "Editar Expediente");
		return "Expediente/EditarExpediente";
	}
}
