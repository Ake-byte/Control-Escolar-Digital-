package com.controldigital.app.controllers;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.controldigital.app.models.entity.Producto;
import com.controldigital.app.models.entity.Usuario;
import com.controldigital.app.service.IProductoService;
import com.controldigital.app.service.IUploadFileService;
import com.controldigital.app.service.IUsuarioService;

@Controller
@RequestMapping("/Productividad")
public class ProductividadController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IUploadFileService uploadFileService;

	@GetMapping(value = "/ListadoProductividad")
	public String verPerfil(Model model, Authentication authentication, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Usuario usuario = usuarioService.findByEmail(auth.getName());

		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "Datos de Usuario");
		return "Productividad/ListadoProductividad";
	}

	@GetMapping(value = "/formProductividad")
	public String crearProducto(Map<String, Object> model) {

		Producto producto = new Producto();
		List<String> opcionesProducto = new ArrayList<>();

		opcionesProducto.add("Artículo");
		opcionesProducto.add("Asistencia Congreso");
		opcionesProducto.add("Capítulo de Libro");
		opcionesProducto.add("Curso Externo");
		opcionesProducto.add("Estancia");
		opcionesProducto.add("Solicitud Patente");
		opcionesProducto.add("Otro");

		((Model) model).addAttribute("opcionesProducto", opcionesProducto);
		((Model) model).addAttribute("producto", producto);
		((Model) model).addAttribute("titulo", "Editar Datos");
		return "Productividad/formProductividad";
	}

	@GetMapping(value = "/formProductividad/{id}")
	public String editarDatos(@PathVariable(value = "id") Long idUsuario, Map<String, Object> model) {

		Usuario usuario = usuarioService.findOne(idUsuario);

		((Model) model).addAttribute("usuario", usuario);
		((Model) model).addAttribute("titulo", "Editar Datos");
		return "Productividad/formProductividad";
	}

	@PostMapping(value = "/formProductividad")
	public String guardarProducto(@Valid Producto producto, BindingResult result, Model model,
			@RequestParam("file") MultipartFile archivo ,Authentication authentication, HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Usuario usuario = usuarioService.findByEmail(auth.getName());

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Editar Datos");
			return "Productividad/formProductividad";
		}

		String uniqueFilename = null;
		if (!archivo.isEmpty()) {

			if (producto.getId() != null && producto.getId() > 0 && producto.getArchivoProducto() != null
					&& producto.getArchivoProducto().length() > 0) {

				uploadFileService.delete(producto.getArchivoProducto());
			}
			
			try {
				uniqueFilename = uploadFileService.copy(archivo);
			} catch (IOException e) {
				e.printStackTrace();
			}
			producto.setArchivoProducto(uniqueFilename);
		} else {
			if(producto.getArchivoProducto() == null) {
				producto.setArchivoProducto("");
			}
		}
		
		producto.setUsers(usuario);
		productoService.save(producto);
		productoService.saveUsuario(usuario);

		return "redirect:ListadoProductividad";
	}
}