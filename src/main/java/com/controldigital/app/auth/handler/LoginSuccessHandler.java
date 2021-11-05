package com.controldigital.app.auth.handler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import com.controldigital.app.models.entity.Usuario;
import com.controldigital.app.service.IUsuarioService;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

	@Autowired
	private IUsuarioService usuarioService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		SessionFlashMapManager flashMapManager = new SessionFlashMapManager();

		FlashMap flashMap = new FlashMap();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Usuario usuario = usuarioService.findByEmail(auth.getName());		

		if (usuario.getEmail().equals(auth.getName())) {
			if (usuario.getEnabled().equals(false)) {
				usuarioInhabilitado();
			}
			flashMap.put("success", "Bienvenido " + usuario.getNombre() + " " + usuario.getApellidoPaterno() + " "
					+ usuario.getApellidoMaterno() + ", has iniciado sesión con éxito.");

			flashMapManager.saveOutputFlashMap(flashMap, request, response);

			super.onAuthenticationSuccess(request, response, authentication);
		}
	}
	
	public String usuarioInhabilitado() {
		return "login";
	}

}
