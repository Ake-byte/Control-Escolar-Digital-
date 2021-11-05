package com.controldigital.app.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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

import com.controldigital.app.models.entity.Role;
import com.controldigital.app.models.entity.Usuario;
import com.controldigital.app.service.IRoleService;
import com.controldigital.app.service.IUsuarioService;
import com.controldigital.app.util.MailSenderService;



@Controller
public class LoginController {

	@Autowired
	private IRoleService roleService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired(required = true)
	private MailSenderService mailService;

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

	@GetMapping(value = "/register")
	public String register(Map<String, Object> model) {
		Usuario usuario = new Usuario();

		model.put("usuario", usuario);
		model.put("titulo", "Registro de Usuario");
		return "register";
	}

	@PostMapping(value = "/register")
	public String guardar(@Valid Usuario usuario, RedirectAttributes flash, BindingResult result, Model model,
			SessionStatus status) throws Exception {

		List<Usuario> usuarios = usuarioService.findall();

		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getEmail().equals(usuario.getEmail())) {
				flash.addFlashAttribute("error", "Ya existe un usuario con esa dirección de correo electrónico.");
				return "redirect:/register";
			}
		}

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Registro de Usuario");
			return "register";
		}

		Role role = new Role();

		role.setAuthority("ROLE_USER1");
		role.setUsers(usuario);
		role.setAuthorityName("Usuario Registrado");
		
		usuario.setRoles(role);
		//usuario.setNombreRole("Usuario Registrado");
		usuario.setEnabled(true);

		String password = usuario.getPassword();

		String bcryptPassword = passwordEncoder.encode(password);

		usuario.setPassword(bcryptPassword);

		roleService.saveUsuario(usuario);
		roleService.save(role);
		mailService.sendEmail(usuario.getEmail(), "Bienvenido al sistema BD-LNCAE",
				"Tu permiso actual es: Usuario Registrado", usuario);

		status.setComplete();

		return "redirect:index";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {

		return "index";
	}


}
