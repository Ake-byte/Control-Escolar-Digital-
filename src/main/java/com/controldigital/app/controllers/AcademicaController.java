package com.controldigital.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.controldigital.app.models.entity.Role;
import com.controldigital.app.models.entity.Usuario;
import com.controldigital.app.service.IRoleService;
import com.controldigital.app.service.IUsuarioService;


@Controller
@RequestMapping("/Academica")
public class AcademicaController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IRoleService roleService;
	
	@GetMapping(value = "/InformacionAcademica")
	public String verPerfil(Model model, Authentication authentication, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Usuario usuario = usuarioService.findByEmail(auth.getName());
		
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "Datos de Usuario");
		return "Academica/InformacionAcademica";
	}
	
	@GetMapping(value = "/EditarInformacionAcademica/{id}")
	public String editarDatos(@PathVariable(value = "id") Long idUsuario, Map<String, Object> model) {

		Usuario usuario = usuarioService.findOne(idUsuario);
		
		((Model) model).addAttribute("usuario", usuario);
		((Model) model).addAttribute("titulo", "Editar Datos");
		return "Academica/EditarInformacionAcademica";
	}

	@PostMapping(value = "/EditarInformacionAcademica")
	public String guardarDatos(@Valid Usuario usuario, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Editar Datos");
			return "EditarInformacionAcademica";
		}

		usuarioService.save(usuario);

		return "redirect:index";
	}
}
