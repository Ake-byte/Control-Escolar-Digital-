package com.controldigital.app.service;

import java.util.List;

import com.controldigital.app.models.entity.Producto;
import com.controldigital.app.models.entity.Usuario;

public interface IProductoService {

	public List<Producto> findAll();
	
	public void save(Producto producto);
	
	public Producto findOne(Long id);

	public void delete(Long id);
	
	public void saveUsuario(Usuario usuario);
}
