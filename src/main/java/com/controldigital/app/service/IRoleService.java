package com.controldigital.app.service;

import java.util.List;

import com.controldigital.app.models.entity.Role;
import com.controldigital.app.models.entity.Usuario;


public interface IRoleService {

	public List<Role> findAll();

	public void save(Role role);

	public void saveUsuario(Usuario usuario);
	
	public Role findOne(Long id);

	public void delete(Long id);

	public List<Role> findUsuarioByRole(String authority);
}
