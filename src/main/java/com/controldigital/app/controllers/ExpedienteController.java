package com.controldigital.app.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.controldigital.app.models.entity.Expediente;
import com.controldigital.app.service.IExpedienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.controldigital.app.models.entity.Usuario;
import com.controldigital.app.service.IUsuarioService;

import java.time.LocalDate;
import java.util.Map;

import static com.controldigital.app.util.Fecha.*;

/**
 * Esta clase sirve para acceder a datos del expediente de los alumnos
 */
@Controller
@RequestMapping("/Expediente")
public class ExpedienteController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IExpedienteService expedienteService;

    /**
     *
     * @param model Parámetro que recibe la vista
     *              /src/resources/templates/Expediente/MiExpediente.html
     * @param authentication Parámetro que permite conocer las credenciales del usuario
     *                       cuando este accede al sistema
     * @param request Permite pasar el objeto al controlador
     * @return
     */
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

		Expediente usuarioExpediente = expedienteService.findExpedienteByUserId(usuario.getId());

		((Model) model).addAttribute("usuarioExpediente", usuarioExpediente);
		((Model) model).addAttribute("titulo", "Editar Expediente");
		return "Expediente/EditarExpediente";
	}

	@PostMapping(value = "/EditarExpediente")
	public String guardarDatos(@Valid Expediente expediente, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Editar Datos");
			return "Personal/EditarInformacionSistema";
		}

		LocalDate date = currentDate();
		Integer enrollYear = Integer.parseInt(expediente.getNumeroRegistro().substring(1,3)) + 2000;
		if(enrollYear != date.getYear()){
			if(expediente.getNumeroRegistro().charAt(0) == 'A'){
				if(mesesA().contains(date.getMonth())){
					expediente.setNumSemestre(((date.getYear() - enrollYear) * 2) + 1);
				} else {
					expediente.setNumSemestre(((date.getYear() - enrollYear) * 2));
				}
			} else {
				if(mesesB().contains(date.getMonth())){
					expediente.setNumSemestre(((date.getYear() - enrollYear) * 2) + 1);
				} else {
					expediente.setNumSemestre(((date.getYear() - enrollYear) * 2));
				}
			}
		} else {
			if(expediente.getNumeroRegistro() == "1"){
				expediente.setNumSemestre(2);
            } else {
			expediente.setNumSemestre(1);
			}
		}

		expedienteService.save(expediente);

		return "redirect:MiExpediente";
	}
}
