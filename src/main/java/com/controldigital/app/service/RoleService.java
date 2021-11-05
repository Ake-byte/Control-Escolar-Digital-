package com.controldigital.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.controldigital.app.models.dao.IRoleDAO;
import com.controldigital.app.models.dao.IUsuarioDAO;
import com.controldigital.app.models.entity.Role;
import com.controldigital.app.models.entity.Usuario;

@Service
public class RoleService implements IRoleService{

	@Autowired
	private IRoleDAO roleDAO;
	
	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	@Override
	@Transactional(readOnly=true)
	public List<Role> findAll() {
		return (List<Role>) roleDAO.findAll();
	}

	@Override
	@Transactional
	public void save(Role role) {
		roleDAO.save(role);
	}

	@Override
	@Transactional
	public void saveUsuario(Usuario usuario) {
		usuarioDAO.save(usuario);
	}

	@Override
	@Transactional(readOnly=true)
	public Role findOne(Long id) {
		return roleDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		roleDAO.deleteById(id);
	}

	@Override
	public List<Role> findUsuarioByRole(String authority) {
		return roleDAO.findUsuarioByRole(authority);
	}

}
