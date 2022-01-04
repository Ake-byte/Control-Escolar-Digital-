package com.controldigital.app.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.controldigital.app.models.entity.FileStatus;
import com.controldigital.app.service.IUploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.controldigital.app.models.entity.Usuario;
import com.controldigital.app.service.IUsuarioService;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/Personal")
public class PersonalController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IUploadFileService uploadFileService;

	@GetMapping(value = "/InformacionPersonal")
	public String verPerfil(Model model, Authentication authentication, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Usuario usuario = usuarioService.findByEmail(auth.getName());
		
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "Datos Personales");
		return "Personal/InformacionPersonal";
	}

	@GetMapping(value = "/EditarInformacionPersonal")
	public String editarDatos(Map<String, Object> model, Authentication authentication, HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Usuario usuario = usuarioService.findByEmail(auth.getName());
		
		List<String> opcionGenero = new ArrayList<>();
		opcionGenero.add("Hombre");
		opcionGenero.add("Mujer");
		opcionGenero.add("Otro");
		

		((Model) model).addAttribute("usuario", usuario);
		((Model) model).addAttribute("opcionGenero", opcionGenero);
		((Model) model).addAttribute("titulo", "Editar Datos");
		return "Personal/EditarInformacionPersonal";
	}

	@PostMapping(value = "/EditarInformacionPersonal")
	public String guardarDatos(@Valid Usuario usuario, BindingResult result, Model model,
							   @RequestParam("files") MultipartFile[] files) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Editar Datos");
			return "Personal/EditarInformacionPersonal";
		}


		if(usuario.getId() != null && usuario.getId() > 0){
			for(int i = 0; i < files.length; i++){
				if(!files[0].isEmpty()){
					if(usuario.getFotoActual() != null && usuario.getFotoActual().length() > 0){
						uploadFileService.delete(usuario.getFotoActual());
					}
					String uniqueFilename = null;
					try {
						uniqueFilename = uploadFileService.copy(files[0]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					usuario.setFotoActual(uniqueFilename);

					usuario.setFotoStatus(FileStatus.YELLOW);
				} else {
					if(usuario.getFotoActual() == null){
						usuario.setFotoActual("");
						usuario.setFotoStatus(FileStatus.RED);
					}
				}
				if(!files[1].isEmpty()){
					if(usuario.getActaNacimiento() != null && usuario.getActaNacimiento().length() > 0){
						uploadFileService.delete(usuario.getActaNacimiento());
					}
					String uniqueFilename = null;
					try {
						uniqueFilename = uploadFileService.copy(files[1]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					usuario.setActaNacimiento(uniqueFilename);

					usuario.setActaStatus(FileStatus.YELLOW);
				} else {
					if(usuario.getActaNacimiento() == null){
						usuario.setActaNacimiento("");
						usuario.setActaStatus(FileStatus.RED);
					}
				}
				if(!files[2].isEmpty()){
					if(usuario.getPasaporte() != null && usuario.getPasaporte().length() > 0){
						uploadFileService.delete(usuario.getPasaporte());
					}
					String uniqueFilename = null;
					try {
						uniqueFilename = uploadFileService.copy(files[2]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					usuario.setPasaporte(uniqueFilename);

					usuario.setPasaporteStatus(FileStatus.YELLOW);
				} else {
					if(usuario.getPasaporte() == null){
						usuario.setPasaporte("");
						usuario.setPasaporteStatus(FileStatus.RED);
					}
				}
				if(!files[3].isEmpty()){
					if(usuario.getCedulaCURP() != null && usuario.getCedulaCURP().length() > 0){
						uploadFileService.delete(usuario.getCedulaCURP());
					}
					String uniqueFilename = null;
					try {
						uniqueFilename = uploadFileService.copy(files[3]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					usuario.setCedulaCURP(uniqueFilename);

					usuario.setCurpStatus(FileStatus.YELLOW);
				} else {
					if(usuario.getCedulaCURP() == null){
						usuario.setCedulaCURP("");
						usuario.setCurpStatus(FileStatus.RED);
					}
				}
			}
		}

		usuarioService.save(usuario);

		return "redirect:InformacionPersonal";
	}

}
