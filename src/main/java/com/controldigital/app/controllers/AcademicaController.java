package com.controldigital.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.controldigital.app.models.entity.*;
import com.controldigital.app.service.IUploadFileService;
import com.controldigital.app.service.InfoAcademicaService;
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

import com.controldigital.app.service.IRoleService;
import com.controldigital.app.service.IUsuarioService;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/Academica")
public class AcademicaController {
	
	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private InfoAcademicaService infoAcademicaService;

	@Autowired
	private IUploadFileService uploadFileService;
	
	@Autowired
	private IRoleService roleService;
	
	@GetMapping(value = "/InformacionAcademica")
	public String verPerfil(Model model, Authentication authentication, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Usuario usuario = usuarioService.findByEmail(auth.getName());

		InfoAcademica usuarioInfoAcademica = infoAcademicaService.findInfoAcademicaByUserId(usuario.getId());
		
		model.addAttribute("usuarioInfoAcademica", usuarioInfoAcademica);
		model.addAttribute("titulo", "Datos de Usuario");
		return "Academica/InformacionAcademica";
	}
	
	@GetMapping(value = "/EditarInformacionAcademica/{id}")
	public String editarDatos(Map<String, Object> model, Authentication authentication, HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Usuario usuario = usuarioService.findByEmail(auth.getName());

		InfoAcademica infoAcademica = infoAcademicaService.findInfoAcademicaByUserId(usuario.getId());
		
		((Model) model).addAttribute("infoAcademica", infoAcademica);
		((Model) model).addAttribute("titulo", "Editar Información Académica");
		return "Academica/EditarInformacionAcademica";
	}

	@PostMapping(value = "/EditarInformacionAcademica")
	public String guardarDatos(@Valid InfoAcademica infoAcademica, BindingResult result, Model model,
							   @RequestParam("files") MultipartFile[] files) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Editar Datos");
			return "EditarInformacionAcademica";
		}

		if(infoAcademica.getId() != null && infoAcademica.getId() > 0){
			for(int i = 0; i < files.length; i++){
				if(!files[0].isEmpty()){
					if(infoAcademica.getCalificacionesLicenciatura() != null && infoAcademica.getCalificacionesLicenciatura().length() > 0){
						uploadFileService.delete(infoAcademica.getCalificacionesLicenciatura());
					}
					String uniqueFilename = null;
					try {
						uniqueFilename = uploadFileService.copy(files[0]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					infoAcademica.setCalificacionesLicenciatura(uniqueFilename);

					infoAcademica.setCalificacionesLicenciaturaStatus(FileStatus.YELLOW);
				} else {
					if(infoAcademica.getCalificacionesLicenciatura() == null){
						infoAcademica.setCalificacionesLicenciatura("");
						infoAcademica.setCalificacionesLicenciaturaStatus(FileStatus.RED1);
					}
				}
				if(!files[1].isEmpty()){
					if(infoAcademica.getDiplomaLicenciatura() != null && infoAcademica.getDiplomaLicenciatura().length() > 0){
						uploadFileService.delete(infoAcademica.getDiplomaLicenciatura());
					}
					String uniqueFilename = null;
					try {
						uniqueFilename = uploadFileService.copy(files[1]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					infoAcademica.setDiplomaLicenciatura(uniqueFilename);

					infoAcademica.setDiplomaLicenciaturaStatus(FileStatus.YELLOW);
				} else {
					if(infoAcademica.getDiplomaLicenciatura() == null){
						infoAcademica.setDiplomaLicenciatura("");
						infoAcademica.setDiplomaLicenciaturaStatus(FileStatus.RED1);
					}
				}
				if(!files[2].isEmpty()){
					if(infoAcademica.getCedulaLicenciatura() != null && infoAcademica.getCedulaLicenciatura().length() > 0){
						uploadFileService.delete(infoAcademica.getCedulaLicenciatura());
					}
					String uniqueFilename = null;
					try {
						uniqueFilename = uploadFileService.copy(files[2]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					infoAcademica.setCedulaLicenciatura(uniqueFilename);

					infoAcademica.setCedulaLicenciaturaStatus(FileStatus.YELLOW);
				} else {
					if(infoAcademica.getCedulaLicenciatura() == null){
						infoAcademica.setCedulaLicenciatura("");
						infoAcademica.setCedulaLicenciaturaStatus(FileStatus.RED1);
					}
				}
				if(!files[3].isEmpty()){
					if(infoAcademica.getAcreditacionIngles() != null && infoAcademica.getAcreditacionIngles().length() > 0){
						uploadFileService.delete(infoAcademica.getAcreditacionIngles());
					}
					String uniqueFilename = null;
					try {
						uniqueFilename = uploadFileService.copy(files[3]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					infoAcademica.setAcreditacionIngles(uniqueFilename);

					infoAcademica.setAcreditacionInglesStatus(FileStatus.YELLOW);
				} else {
					if(infoAcademica.getAcreditacionIngles() == null){
						infoAcademica.setAcreditacionIngles("");
						infoAcademica.setAcreditacionInglesStatus(FileStatus.RED1);
					}
				}
				if(!files[4].isEmpty()){
					if(infoAcademica.getCalificacionesMaestria() != null && infoAcademica.getCalificacionesMaestria().length() > 0){
						uploadFileService.delete(infoAcademica.getCalificacionesMaestria());
					}
					String uniqueFilename = null;
					try {
						uniqueFilename = uploadFileService.copy(files[4]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					infoAcademica.setCalificacionesMaestria(uniqueFilename);

					infoAcademica.setCalificacionesMaestriaStatus(FileStatus.YELLOW);
				} else {
					if(infoAcademica.getCalificacionesMaestria() == null){
						infoAcademica.setCalificacionesMaestria("");
						infoAcademica.setCalificacionesMaestriaStatus(FileStatus.RED1);
					}
				}
				if(!files[5].isEmpty()){
					if(infoAcademica.getActaExamenMaestria() != null && infoAcademica.getActaExamenMaestria().length() > 0){
						uploadFileService.delete(infoAcademica.getActaExamenMaestria());
					}
					String uniqueFilename = null;
					try {
						uniqueFilename = uploadFileService.copy(files[5]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					infoAcademica.setActaExamenMaestria(uniqueFilename);

					infoAcademica.setActaExamenMaestriaStatus(FileStatus.YELLOW);
				} else {
					if(infoAcademica.getActaExamenMaestria() == null){
						infoAcademica.setActaExamenMaestria("");
						infoAcademica.setActaExamenMaestriaStatus(FileStatus.RED1);
					}
				}
				if(!files[6].isEmpty()){
					if(infoAcademica.getDiplomaMaestria() != null && infoAcademica.getDiplomaMaestria().length() > 0){
						uploadFileService.delete(infoAcademica.getDiplomaMaestria());
					}
					String uniqueFilename = null;
					try {
						uniqueFilename = uploadFileService.copy(files[6]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					infoAcademica.setDiplomaMaestria(uniqueFilename);

					infoAcademica.setDiplomaMaestriaStatus(FileStatus.YELLOW);
				} else {
					if(infoAcademica.getDiplomaMaestria() == null){
						infoAcademica.setDiplomaMaestria("");
						infoAcademica.setDiplomaMaestriaStatus(FileStatus.RED1);
					}
				}
				if(!files[7].isEmpty()){
					if(infoAcademica.getCedulaMaestria() != null && infoAcademica.getCedulaMaestria().length() > 0){
						uploadFileService.delete(infoAcademica.getCedulaMaestria());
					}
					String uniqueFilename = null;
					try {
						uniqueFilename = uploadFileService.copy(files[7]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					infoAcademica.setCedulaMaestria(uniqueFilename);

					infoAcademica.setCedulaMaestriaStatus(FileStatus.YELLOW);
				} else {
					if(infoAcademica.getCedulaMaestria() == null){
						infoAcademica.setCedulaMaestria("");
						infoAcademica.setCedulaMaestriaStatus(FileStatus.RED1);
					}
				}
			}
		}

		infoAcademicaService.save(infoAcademica);

		return "redirect:InformacionAcademica";
	}

	@GetMapping(value = "/descargarArchivo/{tipoArchivo}/{id}")
	public ResponseEntity<Resource> descargarArchivo(@PathVariable String tipoArchivo, @PathVariable Long id, HttpServletRequest request) {

		InfoAcademica infoAcademica = infoAcademicaService.findInfoAcademicaByUserId(id);

		String filename = null;

		if(tipoArchivo.equals("calificacionesLicenciatura")){
			filename = infoAcademica.getCalificacionesLicenciatura();
		}
		else if(tipoArchivo.equals("diplomaLicenciatura")){
			filename = infoAcademica.getDiplomaLicenciatura();
		}
		else if(tipoArchivo.equals("cedulaLicenciatura")){
			filename = infoAcademica.getCedulaLicenciatura();
		}
		else if(tipoArchivo.equals("acreditacionIngles")){
			filename = infoAcademica.getAcreditacionIngles();
		}
		else if(tipoArchivo.equals("calificacionesMaestria")){
			filename = infoAcademica.getCalificacionesMaestria();
		}
		else if(tipoArchivo.equals("actaExamenMaestria")){
			filename = infoAcademica.getActaExamenMaestria();
		}
		else if(tipoArchivo.equals("diplomaMaestria")){
			filename = infoAcademica.getDiplomaMaestria();
		}
		else if(tipoArchivo.equals("cedulaMaestria")){
			filename = infoAcademica.getCedulaMaestria();
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
