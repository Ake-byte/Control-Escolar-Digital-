package com.controldigital.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
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
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.controldigital.app.models.entity.Usuario;
import com.controldigital.app.service.IUsuarioService;
import org.springframework.web.multipart.MultipartFile;

/**
 * Este controlador sirve para que el alumno acceda a su "Información Personal"
 */
@Controller
@RequestMapping("/Personal")
public class PersonalController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private InforPersonalService infoPersonalService;

	@Autowired
	private IUploadFileService uploadFileService;

	/**
	 * Método que permite ver la información personal del usuario
	 * @param model objeto que recibe la vista
	 * @param authentication
	 * @param request
	 * @return /src/main/resources/templates/Personal/InformacionPersonal.html
	 */
	@GetMapping(value = "/InformacionPersonal")
	public String verPerfil(Model model, Authentication authentication, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Usuario usuario = usuarioService.findByEmail(auth.getName());

		InfoPersonal usuarioInfoPersonal = infoPersonalService.findInfoPersonalByUserId(usuario.getId());

		model.addAttribute("usuario", usuarioInfoPersonal);
		model.addAttribute("titulo", "Información Personal");
		return "Personal/InformacionPersonal";
	}

	/**
	 * Método que abre el formulario para editar/actualizar datos del usuario
	 * @param model
	 * @param authentication
	 * @param request
	 * @return /src/main/resources/templates/Personal/EditarInformacionPersonal.html
	 */
	@GetMapping(value = "/EditarInformacionPersonal")
	public String editarDatos(Map<String, Object> model, Authentication authentication, HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Usuario usuario = usuarioService.findByEmail(auth.getName());
		InfoPersonal infoPersonal = infoPersonalService.findInfoPersonalByUserId(usuario.getId());


		List<String> opcionGenero = new ArrayList<>();
		opcionGenero.add("Hombre");
		opcionGenero.add("Mujer");
		opcionGenero.add("Otro");
		

		((Model) model).addAttribute("infoPersonal", infoPersonal);
		((Model) model).addAttribute("opcionGenero", opcionGenero);
		((Model) model).addAttribute("titulo", "Editar Datos");
		return "Personal/EditarInformacionPersonal";
	}

	/**
	 * Método que guarda los datos que fueron insertados en el formulario "EditarInformaciónPersonal.html"
	 * @param infoPersonal objeto del tipo InfoPersonal
	 * @param result
	 * @param model objeto que recibe la vista
	 * @param files Arreglo de archivos de caracter personal del usuario
	 * @return /src/main/resources/templates/Personal/InfoPersonal.html
	 */
	@PostMapping(value = "/EditarInformacionPersonal")
	public String guardarDatos(@Valid InfoPersonal infoPersonal, BindingResult result, Model model,
							   @RequestParam("files") MultipartFile[] files) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Editar Datos");
			return "Personal/EditarInformacionPersonal";
		}

		if(infoPersonal.getId() != null && infoPersonal.getId() > 0){
			for(int i = 0; i < files.length; i++){
				if(!files[0].isEmpty()){
					if(infoPersonal.getFotoActual() != null && infoPersonal.getFotoActual().length() > 0){
						uploadFileService.delete(infoPersonal.getFotoActual());
					}
					String uniqueFilename = null;
					try {
						uniqueFilename = uploadFileService.copy(files[0]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					infoPersonal.setFotoActual(uniqueFilename);

					infoPersonal.setFotoStatus(FileStatus.YELLOW);
				} else {
					if(infoPersonal.getFotoActual() == null){
						infoPersonal.setFotoActual("");
						infoPersonal.setFotoStatus(FileStatus.RED1);
					}
				}
				if(!files[1].isEmpty()){
					if(infoPersonal.getActaNacimiento() != null && infoPersonal.getActaNacimiento().length() > 0){
						uploadFileService.delete(infoPersonal.getActaNacimiento());
					}
					String uniqueFilename = null;
					try {
						uniqueFilename = uploadFileService.copy(files[1]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					infoPersonal.setActaNacimiento(uniqueFilename);

					infoPersonal.setActaStatus(FileStatus.YELLOW);
				} else {
					if(infoPersonal.getActaNacimiento() == null){
						infoPersonal.setActaNacimiento("");
						infoPersonal.setActaStatus(FileStatus.RED1);
					}
				}
				if(!files[2].isEmpty()){
					if(infoPersonal.getPasaporte() != null && infoPersonal.getPasaporte().length() > 0){
						uploadFileService.delete(infoPersonal.getPasaporte());
					}
					String uniqueFilename = null;
					try {
						uniqueFilename = uploadFileService.copy(files[2]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					infoPersonal.setPasaporte(uniqueFilename);

					infoPersonal.setPasaporteStatus(FileStatus.YELLOW);
				} else {
					if(infoPersonal.getPasaporte() == null){
						infoPersonal.setPasaporte("");
						infoPersonal.setPasaporteStatus(FileStatus.RED1);
					}
				}
				if(!files[3].isEmpty()){
					if(infoPersonal.getCedulaCURP() != null && infoPersonal.getCedulaCURP().length() > 0){
						uploadFileService.delete(infoPersonal.getCedulaCURP());
					}
					String uniqueFilename = null;
					try {
						uniqueFilename = uploadFileService.copy(files[3]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					infoPersonal.setCedulaCURP(uniqueFilename);

					infoPersonal.setCurpStatus(FileStatus.YELLOW);
				} else {
					if(infoPersonal.getCedulaCURP() == null){
						infoPersonal.setCedulaCURP("");
						infoPersonal.setCurpStatus(FileStatus.RED1);
					}
				}
			}
		}

		infoPersonalService.save(infoPersonal);

		return "redirect:InformacionPersonal";
	}

	/**
	 * Método que le permite al usuario descargar los archivos que tiene guardados en el sistema.
	 * @param tipoArchivo parámetro que se refiere al archivo que el usuario solicitó descargar
	 * @param id parámetro que indica el identificador único del usuraio
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/descargarArchivo/{tipoArchivo}/{id}")
	public ResponseEntity<Resource> descargarArchivo(@PathVariable String tipoArchivo, @PathVariable Long id, HttpServletRequest request) {

		InfoPersonal infoPersonal = infoPersonalService.findInfoPersonalByUserId(id);

		String filename = null;

		if(tipoArchivo.equals("Foto")){
			filename = infoPersonal.getFotoActual();
		}
		else if(tipoArchivo.equals("Acta")){
			filename = infoPersonal.getActaNacimiento();
		}
		else if(tipoArchivo.equals("Pasaporte")){
			filename = infoPersonal.getPasaporte();
		}
		else if(tipoArchivo.equals("Curp")){
			filename = infoPersonal.getCedulaCURP();
		}

		Resource recurso = null;
		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(recurso.getFile().getAbsolutePath());
		} catch (IOException ex) {
			// logger.info("Could not determine file type.");
		}
		// Fallback to the default content type if type could not be determined
		if (contentType == null) {

			contentType = "application/octet-stream";

		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);

	}


}
