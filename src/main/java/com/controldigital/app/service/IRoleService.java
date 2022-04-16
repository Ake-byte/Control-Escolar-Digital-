package com.controldigital.app.service;

import java.util.List;

import com.controldigital.app.models.entity.Role;
import com.controldigital.app.models.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IRoleService {

	public List<Role> findAll();

	public void save(Role role);

	public void saveUsuario(Usuario usuario);
	
	public Role findOne(Long id);

	public void delete(Long id);

	public List<Role> findUsuarioByRole(String authority);

	public Page<Role> findUsuarioByRolePage(String authority, Pageable pageable);

	public Page<Role> findUsuarioByRolePageDisabled(String authority, Pageable pageable);

    Role findRoleByUserId(Long id);
}
