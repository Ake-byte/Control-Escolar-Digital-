package com.controldigital.app.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controldigital.app.models.entity.Usuario;
import com.controldigital.app.service.IUsuarioService;
import com.controldigital.app.util.MailSenderService;

import net.bytebuddy.utility.RandomString;

@Controller
public class ForgotPasswordController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired(required = true)
	private MailSenderService mailService;

	
	@GetMapping(value = "/newPassword")
	public String newPassword(Map<String, Object> model) {
		// Usuario usuario = new Usuario();

		// model.put("usuario", usuario);
		model.put("titulo", "Recuperar Acceso");
		return "newPassword";
	}

	@PostMapping(value = "/newPassword")
	public String guardarNuevaPassword(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("email") String email, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) throws Exception {

		String siteURL = request.getRequestURL().toString();
		String token = RandomString.make(30);

		String link = siteURL.replace(request.getServletPath(), "") + "/newPasswordForm?token=" + token;

		Usuario usuario = usuarioService.findByEmail(email);
		if (usuario != null) {
			flash.addFlashAttribute("info",
					"Se ha enviado información con respecto al cambio de contraseña a la dirección de correo electrónico proporcionada.");
			String content = "<p>Hola,</p>" + "<p>Has solicitado un cambio de contrseña.</p>"
					+ "<p>Da click en la dirección de abajo para realizar el cambio:</p>" + "<p><a href=\"" + link
					+ "\">Cambiar contraseña</a></p>" + "<br>"
					+ "<p>Ignora este correo si no solicitaste realizar este cambio.</p>";

			mailService.sendEmail(usuario.getEmail(), "Cambiar contraseña", content, usuario);

			usuario.setResetPasswordToken(token);
			usuarioService.save(usuario);

			return "redirect:/login";
		}
		flash.addFlashAttribute("error",
				"La dirección de correo electrónico que proporcionó no existe en el sistema, por favor registrese.");
		return "redirect:/login";
	}

	@GetMapping(value = "/newPasswordForm")
	public String newPasswordForm(@RequestParam(name = "token") String token, Map<String, Object> model) {

		Usuario usuario = usuarioService.findByResetPasswordToken(token);

		if (usuario == null) {

			return "login";
		}

		model.put("usuario", usuario);
		model.put("token", token);
		model.put("titulo", "Recuperar Acceso");
		return "newPasswordForm";
	}

	@PostMapping(value = "/newPasswordForm")
	public String SaveNewPassword(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("password") String newPassword, @ModelAttribute("token") String token, BindingResult result,
			Model model, RedirectAttributes flash, SessionStatus status) {

		Usuario usuario = usuarioService.findByResetPasswordToken(token);

		if (usuario == null) {
			flash.addFlashAttribute("error", "No se encontró el token.");
			return "redirect:/";
		}

		usuario.setResetPasswordToken(null);

		// String password = usuario.setPassword(newPassword);
		String bcryptPassword = passwordEncoder.encode(newPassword);
		usuario.setPassword(bcryptPassword);

		usuarioService.save(usuario);

		return "login";
	}
}
