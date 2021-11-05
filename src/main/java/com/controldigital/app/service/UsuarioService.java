package com.controldigital.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.controldigital.app.models.dao.IUsuarioDAO;
import com.controldigital.app.models.entity.Usuario;

@Service
public class UsuarioService implements IUsuarioService{

	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	@Override
	@Transactional(readOnly=true)
	public List<Usuario> findall() {
		return (List<Usuario>) usuarioDAO.findAll();
	}

	@Override
	@Transactional
	public void save(Usuario usuario) {
		usuarioDAO.save(usuario);
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario findOne(Long id) {
		return usuarioDAO.findById(id).orElse(null);
	}

	

	@Override
	@Transactional
	public void delete(Long id) {
		usuarioDAO.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario findByEmail(String email) {
		return usuarioDAO.findByEmail(email);
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario findByResetPasswordToken(String token) {
		return usuarioDAO.findByResetPasswordToken(token);
	}

}
