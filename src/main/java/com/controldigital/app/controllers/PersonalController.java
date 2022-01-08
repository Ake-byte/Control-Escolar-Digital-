package com.controldigital.app.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.controldigital.app.models.entity.FileStatus;
import com.controldigital.app.models.entity.InfoPersonal;
import com.controldigital.app.service.IInfoPersonalService;
import com.controldigital.app.service.IUploadFileService;
import com.controldigital.app.service.InforPersonalService;
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
	private InforPersonalService infoPersonalService;

	@Autowired
	private IUploadFileService uploadFileService;

	@GetMapping(value = "/InformacionPersonal")
	public String verPerfil(Model model, Authentication authentication, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Usuario usuario = usuarioService.findByEmail(auth.getName());

		InfoPersonal usuarioInfoPersonal = infoPersonalService.findInfoPersonalByUserId(usuario.getId());

		model.addAttribute("usuario", usuarioInfoPersonal);
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
					if(usuario.getInfoPersonal().getFotoActual() != null && usuario.getInfoPersonal().getFotoActual().length() > 0){
						uploadFileService.delete(usuario.getInfoPersonal().getFotoActual());
					}
					String uniqueFilename = null;
					try {
						uniqueFilename = uploadFileService.copy(files[0]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					usuario.getInfoPersonal().setFotoActual(uniqueFilename);

					usuario.getInfoPersonal().setFotoStatus(FileStatus.YELLOW);
				} else {
					if(usuario.getInfoPersonal().getFotoActual() == null){
						usuario.getInfoPersonal().setFotoActual("");
						usuario.getInfoPersonal().setFotoStatus(FileStatus.RED1);
					}
				}
				if(!files[1].isEmpty()){
					if(usuario.getInfoPersonal().getActaNacimiento() != null && usuario.getInfoPersonal().getActaNacimiento().length() > 0){
						uploadFileService.delete(usuario.getInfoPersonal().getActaNacimiento());
					}
					String uniqueFilename = null;
					try {
						uniqueFilename = uploadFileService.copy(files[1]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					usuario.getInfoPersonal().setActaNacimiento(uniqueFilename);

					usuario.getInfoPersonal().setActaStatus(FileStatus.YELLOW);
				} else {
					if(usuario.getInfoPersonal().getActaNacimiento() == null){
						usuario.getInfoPersonal().setActaNacimiento("");
						usuario.getInfoPersonal().setActaStatus(FileStatus.RED1);
					}
				}
				if(!files[2].isEmpty()){
					if(usuario.getInfoPersonal().getPasaporte() != null && usuario.getInfoPersonal().getPasaporte().length() > 0){
						uploadFileService.delete(usuario.getInfoPersonal().getPasaporte());
					}
					String uniqueFilename = null;
					try {
						uniqueFilename = uploadFileService.copy(files[2]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					usuario.getInfoPersonal().setPasaporte(uniqueFilename);

					usuario.getInfoPersonal().setPasaporteStatus(FileStatus.YELLOW);
				} else {
					if(usuario.getInfoPersonal().getPasaporte() == null){
						usuario.getInfoPersonal().setPasaporte("");
						usuario.getInfoPersonal().setPasaporteStatus(FileStatus.RED1);
					}
				}
				if(!files[3].isEmpty()){
					if(usuario.getInfoPersonal().getCedulaCURP() != null && usuario.getInfoPersonal().getCedulaCURP().length() > 0){
						uploadFileService.delete(usuario.getInfoPersonal().getCedulaCURP());
					}
					String uniqueFilename = null;
					try {
						uniqueFilename = uploadFileService.copy(files[3]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					usuario.getInfoPersonal().setCedulaCURP(uniqueFilename);

					usuario.getInfoPersonal().setCurpStatus(FileStatus.YELLOW);
				} else {
					if(usuario.getInfoPersonal().getCedulaCURP() == null){
						usuario.getInfoPersonal().setCedulaCURP("");
						usuario.getInfoPersonal().setCurpStatus(FileStatus.RED1);
					}
				}
			}
		}

		usuarioService.save(usuario);

		return "redirect:InformacionPersonal";
	}

}
