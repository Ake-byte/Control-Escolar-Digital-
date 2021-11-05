package com.controldigital.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.controldigital.app.models.dao.IProductoDAO;
import com.controldigital.app.models.dao.IUsuarioDAO;
import com.controldigital.app.models.entity.Producto;
import com.controldigital.app.models.entity.Usuario;

@Service
public class ProductoService implements IProductoService{

	@Autowired
	private IProductoDAO productoDao;
	
	@Autowired
	private IUsuarioDAO usuarioDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Producto> findAll() {
		return (List<Producto>) productoDao.findAll();
	}

	@Override
	@Transactional
	public void save(Producto producto) {
		productoDao.save(producto);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Producto findOne(Long id) {
		return productoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		productoDao.deleteById(id);
	}

	@Override
	@Transactional
	public void saveUsuario(Usuario usuario) {
		usuarioDao.save(usuario);
	}

}
