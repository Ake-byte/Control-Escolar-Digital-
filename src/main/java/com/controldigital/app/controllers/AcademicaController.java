package com.controldigital.app.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.controldigital.app.models.entity.FileStatus;
import com.controldigital.app.models.entity.InfoAcademica;
import com.controldigital.app.service.IUploadFileService;
import com.controldigital.app.service.InfoAcademicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.controldigital.app.models.entity.Role;
import com.controldigital.app.models.entity.Usuario;
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
		
		model.addAttribute("usuario", usuarioInfoAcademica);
		model.addAttribute("titulo", "Datos de Usuario");
		return "Academica/InformacionAcademica";
	}
	
	@GetMapping(value = "/EditarInformacionAcademica/{id}")
	public String editarDatos(Map<String, Object> model, Authentication authentication, HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Usuario usuario = usuarioService.findByEmail(auth.getName());
		
		((Model) model).addAttribute("usuario", usuario);
		((Model) model).addAttribute("titulo", "Editar Datos");
		return "Academica/EditarInformacionAcademica";
	}

	@PostMapping(value = "/EditarInformacionAcademica")
	public String guardarDatos(@Valid Usuario usuario, BindingResult result, Model model,
							   @RequestParam("files") MultipartFile[] files) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Editar Datos");
			return "EditarInformacionAcademica";
		}

		if(usuario.getId() != null && usuario.getId() > 0){
			for(int i = 0; i < files.length; i++){
				if(!files[0].isEmpty()){
					if(usuario.getInfoAcademica().getCalificacionesLicenciatura() != null && usuario.getInfoAcademica().getCalificacionesLicenciatura().length() > 0){
						uploadFileService.delete(usuario.getInfoAcademica().getCalificacionesLicenciatura());
					}
					String uniqueFilename = null;
					try {
						uniqueFilename = uploadFileService.copy(files[0]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					usuario.getInfoAcademica().setCalificacionesLicenciatura(uniqueFilename);

					usuario.getInfoAcademica().setCalificacionesLicenciaturaStatus(FileStatus.YELLOW);
				} else {
					if(usuario.getInfoAcademica().getCalificacionesLicenciatura() == null){
						usuario.getInfoAcademica().setCalificacionesLicenciatura("");
						usuario.getInfoAcademica().setCalificacionesLicenciaturaStatus(FileStatus.RED1);
					}
				}
				if(!files[1].isEmpty()){
					if(usuario.getInfoAcademica().getDiplomaLicenciatura() != null && usuario.getInfoAcademica().getDiplomaLicenciatura().length() > 0){
						uploadFileService.delete(usuario.getInfoAcademica().getDiplomaLicenciatura());
					}
					String uniqueFilename = null;
					try {
						uniqueFilename = uploadFileService.copy(files[1]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					usuario.getInfoAcademica().setDiplomaLicenciatura(uniqueFilename);

					usuario.getInfoAcademica().setDiplomaLicenciaturaStatus(FileStatus.YELLOW);
				} else {
					if(usuario.getInfoAcademica().getDiplomaLicenciatura() == null){
						usuario.getInfoAcademica().setDiplomaLicenciatura("");
						usuario.getInfoAcademica().setDiplomaLicenciaturaStatus(FileStatus.RED1);
					}
				}
				if(!files[2].isEmpty()){
					if(usuario.getInfoAcademica().getCedulaLicenciatura() != null && usuario.getInfoAcademica().getCedulaLicenciatura().length() > 0){
						uploadFileService.delete(usuario.getInfoAcademica().getCedulaLicenciatura());
					}
					String uniqueFilename = null;
					try {
						uniqueFilename = uploadFileService.copy(files[2]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					usuario.getInfoAcademica().setCedulaLicenciatura(uniqueFilename);

					usuario.getInfoAcademica().setCedulaLicenciaturaStatus(FileStatus.YELLOW);
				} else {
					if(usuario.getInfoAcademica().getCedulaLicenciatura() == null){
						usuario.getInfoAcademica().setCedulaLicenciatura("");
						usuario.getInfoAcademica().setCedulaLicenciaturaStatus(FileStatus.RED1);
					}
				}
				if(!files[3].isEmpty()){
					if(usuario.getInfoAcademica().getAcreditacionIngles() != null && usuario.getInfoAcademica().getAcreditacionIngles().length() > 0){
						uploadFileService.delete(usuario.getInfoAcademica().getAcreditacionIngles());
					}
					String uniqueFilename = null;
					try {
						uniqueFilename = uploadFileService.copy(files[3]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					usuario.getInfoAcademica().setAcreditacionIngles(uniqueFilename);

					usuario.getInfoAcademica().setAcreditacionInglesStatus(FileStatus.YELLOW);
				} else {
					if(usuario.getInfoAcademica().getAcreditacionIngles() == null){
						usuario.getInfoAcademica().setAcreditacionIngles("");
						usuario.getInfoAcademica().setAcreditacionInglesStatus(FileStatus.RED1);
					}
				}
				if(!files[4].isEmpty()){
					if(usuario.getInfoAcademica().getCalificacionesMaestria() != null && usuario.getInfoAcademica().getCalificacionesMaestria().length() > 0){
						uploadFileService.delete(usuario.getInfoAcademica().getCalificacionesMaestria());
					}
					String uniqueFilename = null;
					try {
						uniqueFilename = uploadFileService.copy(files[4]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					usuario.getInfoAcademica().setCalificacionesMaestria(uniqueFilename);

					usuario.getInfoAcademica().setCalificacionesMaestriaStatus(FileStatus.YELLOW);
				} else {
					if(usuario.getInfoAcademica().getCalificacionesMaestria() == null){
						usuario.getInfoAcademica().setCalificacionesMaestria("");
						usuario.getInfoAcademica().setCalificacionesMaestriaStatus(FileStatus.RED1);
					}
				}
				if(!files[5].isEmpty()){
					if(usuario.getInfoAcademica().getActaExamenMaestria() != null && usuario.getInfoAcademica().getActaExamenMaestria().length() > 0){
						uploadFileService.delete(usuario.getInfoAcademica().getActaExamenMaestria());
					}
					String uniqueFilename = null;
					try {
						uniqueFilename = uploadFileService.copy(files[5]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					usuario.getInfoAcademica().setActaExamenMaestria(uniqueFilename);

					usuario.getInfoAcademica().setActaExamenMaestriaStatus(FileStatus.YELLOW);
				} else {
					if(usuario.getInfoAcademica().getActaExamenMaestria() == null){
						usuario.getInfoAcademica().setActaExamenMaestria("");
						usuario.getInfoAcademica().setActaExamenMaestriaStatus(FileStatus.RED1);
					}
				}
				if(!files[6].isEmpty()){
					if(usuario.getInfoAcademica().getDiplomaMaestria() != null && usuario.getInfoAcademica().getDiplomaMaestria().length() > 0){
						uploadFileService.delete(usuario.getInfoAcademica().getDiplomaMaestria());
					}
					String uniqueFilename = null;
					try {
						uniqueFilename = uploadFileService.copy(files[6]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					usuario.getInfoAcademica().setDiplomaMaestria(uniqueFilename);

					usuario.getInfoAcademica().setDiplomaMaestriaStatus(FileStatus.YELLOW);
				} else {
					if(usuario.getInfoAcademica().getDiplomaMaestria() == null){
						usuario.getInfoAcademica().setDiplomaMaestria("");
						usuario.getInfoAcademica().setDiplomaMaestriaStatus(FileStatus.RED1);
					}
				}
				if(!files[7].isEmpty()){
					if(usuario.getInfoAcademica().getCedulaMaestria() != null && usuario.getInfoAcademica().getCedulaMaestria().length() > 0){
						uploadFileService.delete(usuario.getInfoAcademica().getCedulaMaestria());
					}
					String uniqueFilename = null;
					try {
						uniqueFilename = uploadFileService.copy(files[7]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					usuario.getInfoAcademica().setCedulaMaestria(uniqueFilename);

					usuario.getInfoAcademica().setCedulaMaestriaStatus(FileStatus.YELLOW);
				} else {
					if(usuario.getInfoAcademica().getCedulaMaestria() == null){
						usuario.getInfoAcademica().setCedulaMaestria("");
						usuario.getInfoAcademica().setCedulaMaestriaStatus(FileStatus.RED1);
					}
				}
			}
		}

		usuarioService.save(usuario);

		return "redirect:InformacionAcademica";
	}
}
